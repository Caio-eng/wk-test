import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-root',
  templateUrl: './resultados.component.html',
  styleUrls: ['./resultados.component.css']
})

export class ResultadosComponent implements OnInit {

  formulario?: FormGroup;
  mostrarResultado: boolean = false;
  resultado?: Resultado;
  erro: any;

  dataSource: MatTableDataSource<any> = new MatTableDataSource<any>();
  dataImc: MatTableDataSource<any> = new MatTableDataSource<any>();
  dataObesos: MatTableDataSource<any> = new MatTableDataSource<any>();
  dataTipoSangue: MatTableDataSource<any> = new MatTableDataSource<any>();
  dataQuantidadeDoadores: MatTableDataSource<any> = new MatTableDataSource<any>();

  constructor(private formBuilder: FormBuilder, private http: HttpClient) {}

  ngOnInit() {
    this.formulario = this.formBuilder.group({
      jsonData: ['', Validators.required]
    });
  }

  processarJSON(): void {
    if (this.formulario?.invalid) {
      return;
    }

    const jsonData = this.formulario?.value.jsonData;

    try {
      const parsedData = JSON.parse(jsonData);
      this.mostrarResultado = false;

      // Faz a chamada para o backend passando o JSON
      this.http.post('http://localhost:8080/doadores/processar', parsedData)
        .subscribe(
          (response: any) => {
            this.resultado = response;
            if (this.resultado) {
              this.dataSource.data = this.resultado.contagemEstados;
              this.dataImc.data = this.resultado.imcMedioPorFaixaIdade;
              this.dataObesos.data = this.resultado.percentualObesos;
              this.dataTipoSangue.data = this.resultado.mediaIdadePorTipoSanguineo;
              this.dataQuantidadeDoadores.data = this.resultado.quantidadeDoadoresPorTipoSanguineo;
            }
            this.mostrarResultado = true;
          },
          (error: any) => {
            this.erro = error;
            console.error('Erro na chamada para o backend:', error);
          }
        );
    } catch (error) {
      this.erro = error;
      console.error('JSON inválido:', error);
    }
  }
}

export interface Resultado {
  percentualObesos: { mulheres: number; homens: number }[];
  contagemEstados: { estado: string; quantidade: number }[];
  imcMedioPorFaixaIdade: { faixaIdade: string; imcMedio: number }[];
  mediaIdadePorTipoSanguineo: { tipoSanguineo: string; mediaIdade: number }[];
  quantidadeDoadoresPorTipoSanguineo: { quantidade: number; tipoSanguineo: string }[];
}

