package com.tecnico.test.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.tecnico.test.domain.Doador;
import com.tecnico.test.domain.MediaIdadePorTipoSanguineo;

@Service
public class DoadorService {

	public List<Object> contarCandidatosPorEstado(List<Doador> doadores) {
	    Map<String, Integer> contagemEstados = new HashMap<>();

	    for (Doador doador : doadores) {
	        String estado = doador.getEstado();
	        contagemEstados.put(estado, contagemEstados.getOrDefault(estado, 0) + 1);
	    }

	    List<Object> listaContagemEstados = new ArrayList<>();
	    for (Map.Entry<String, Integer> entry : contagemEstados.entrySet()) {
	        String estado = entry.getKey();
	        int quantidade = entry.getValue();
	        Map<String, Object> objetoEstadoQuantidade = new HashMap<>();
	        objetoEstadoQuantidade.put("estado", estado);
	        objetoEstadoQuantidade.put("quantidade", quantidade);
	        listaContagemEstados.add(objetoEstadoQuantidade);
	    }

	    return listaContagemEstados;
	}

	public List<Object> calcularIMCMedioPorFaixaIdade(List<Doador> doadores) {
	    List<Object> resultado = new ArrayList<>();
	    Map<String, List<Double>> imcsPorFaixaIdade = new HashMap<>();

	    for (Doador doador : doadores) {
	        int idade = doador.getIdade();
	        double imc = calcularIMC(doador.getPeso(), doador.getAltura());
	        String faixaIdade = obterFaixaIdade(idade);

	        imcsPorFaixaIdade.computeIfAbsent(faixaIdade, k -> new ArrayList<>()).add(imc);
	    }

	    for (Map.Entry<String, List<Double>> entry : imcsPorFaixaIdade.entrySet()) {
	        String faixaIdade = entry.getKey();
	        List<Double> imcs = entry.getValue();
	        double imcMedio = calcularMedia(imcs);

	        Map<String, Object> faixaIdadeIMC = new HashMap<>();
	        faixaIdadeIMC.put("faixaIdade", faixaIdade);
	        faixaIdadeIMC.put("imcMedio", imcMedio);
	        resultado.add(faixaIdadeIMC);
	    }

	    return resultado;
	}

	public Double calcularIMC(double peso, double altura) {
		return peso / (altura * altura);
	}

	public String obterFaixaIdade(int idade) {
		int faixa = (idade / 10) * 10;
		return faixa + " a " + (faixa + 10);
	}

	public double calcularPercentualObesosPorGenero(List<Doador> doadores, String sexo) {
		long totalCandidatosPorGenero = doadores.stream().filter(c -> c.getSexo().equalsIgnoreCase(sexo)).count();

		long totalObesosPorGenero = doadores.stream().filter(c -> c.getSexo().equalsIgnoreCase(sexo))
				.filter(c -> calcularIMC(c.getPeso(), c.getAltura()) > 30).count();

		if (totalCandidatosPorGenero == 0) {
			return 0;
		}

		return (double) totalObesosPorGenero / totalCandidatosPorGenero * 100;
	}

	public Double calcularMedia(List<Double> valores) {
		double soma = 0;
		for (Double valor : valores) {
			soma += valor;
		}

		return soma / valores.size();
	}

	public List<Object> calcularMediaIdadePorTipoSanguineo(List<Doador> doadores) {
		Map<String, List<Integer>> idadesPorTipoSanguineo = new HashMap<>();

		for (Doador doador : doadores) {
			String tipoSanguineo = doador.getTipoSanguineo();
			System.out.println(tipoSanguineo);
			int idade = doador.getIdade();

			idadesPorTipoSanguineo.computeIfAbsent(tipoSanguineo, k -> new ArrayList<>()).add(idade);
		}

		List<Object> mediaIdadePorTipoSanguineo = new ArrayList<>();

		for (Map.Entry<String, List<Integer>> entry : idadesPorTipoSanguineo.entrySet()) {
			String tipoSanguineo = entry.getKey();
			List<Integer> idades = entry.getValue();

			if (!idades.isEmpty()) {
				double mediaIdade = calcularMediaIdade(idades);
				MediaIdadePorTipoSanguineo resultado = new MediaIdadePorTipoSanguineo();
				resultado.setTipoSanguineo(tipoSanguineo);
				resultado.setMediaIdade(mediaIdade);
				mediaIdadePorTipoSanguineo.add(resultado);
			}
		}

		return mediaIdadePorTipoSanguineo;
	}

	public double calcularMediaIdade(List<Integer> idades) {
		int soma = 0;
		for (int idade : idades) {
			soma += idade;
		}
		return (double) soma / idades.size();
	}

	public List<Object> calcularQuantidadeDoadoresPorTipoSanguineoReceptor(List<Doador> doadores) {
	    List<Object> resultado = new ArrayList<>();
	    Map<String, Integer> quantidadeDoadoresPorTipoSanguineo = new HashMap<>();

	    for (Doador doador : doadores) {
	        String tipoSanguineo = doador.getTipoSanguineo();
	        String[] tiposSanguineosReceptor = obterTiposSanguineosReceptor(tipoSanguineo);

	        for (String tipoSanguineoReceptor : tiposSanguineosReceptor) {
	            quantidadeDoadoresPorTipoSanguineo.put(tipoSanguineoReceptor,
	                    quantidadeDoadoresPorTipoSanguineo.getOrDefault(tipoSanguineoReceptor, 0) + 1);
	        }
	    }

	    for (Map.Entry<String, Integer> entry : quantidadeDoadoresPorTipoSanguineo.entrySet()) {
	        String tipoSanguineo = entry.getKey();
	        int quantidade = entry.getValue();

	        Map<String, Object> tipoSanguineoQuantidade = new HashMap<>();
	        tipoSanguineoQuantidade.put("tipoSanguineo", tipoSanguineo);
	        tipoSanguineoQuantidade.put("quantidade", quantidade);
	        resultado.add(tipoSanguineoQuantidade);
	    }

	    return resultado;
	}

	private String[] obterTiposSanguineosReceptor(String tipoSanguineoDoador) {
		switch (tipoSanguineoDoador) {
		case "A+":
			return new String[] { "A+", "AB+" };
		case "A-":
			return new String[] { "A+", "A-", "AB+", "AB-" };
		case "B+":
			return new String[] { "B+", "AB+" };
		case "B-":
			return new String[] { "B+", "B-", "AB+", "AB-" };
		case "AB+":
			return new String[] { "AB+" };
		case "AB-":
			return new String[] { "AB+", "AB-" };
		case "O+":
			return new String[] { "A+", "B+", "AB+", "O+" };
		case "O-":
			return new String[] { "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-" };
		default:
			return new String[] {};
		}
	}

}
