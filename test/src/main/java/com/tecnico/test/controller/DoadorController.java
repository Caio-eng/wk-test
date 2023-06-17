package com.tecnico.test.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tecnico.test.domain.Doador;
import com.tecnico.test.services.DoadorService;

@RestController
@RequestMapping(value = "/doadores")
public class DoadorController {

	@Autowired
	private DoadorService doadorService;

	@PostMapping("/processar")
	public ResponseEntity<Map<String, List<Object>>> processarDadosCandidatos(@RequestBody List<Doador> doadores) {
	    Map<String, List<Object>> resultadoFinal = new HashMap<>();

	    List<Object> contagemEstados = doadorService.contarCandidatosPorEstado(doadores);
	    resultadoFinal.put("contagemEstados", contagemEstados);

	    List<Object> imcMedioPorFaixaIdade = doadorService.calcularIMCMedioPorFaixaIdade(doadores);
	    resultadoFinal.put("imcMedioPorFaixaIdade", imcMedioPorFaixaIdade);

	    double percentualObesosHomens = doadorService.calcularPercentualObesosPorGenero(doadores, "masculino");
	    double percentualObesosMulheres = doadorService.calcularPercentualObesosPorGenero(doadores, "feminino");

	    Map<String, Double> percentualObesos = new HashMap<>();
	    percentualObesos.put("homens", percentualObesosHomens);
	    percentualObesos.put("mulheres", percentualObesosMulheres);
	    List<Object> percentualObesosList = new ArrayList<>();
	    percentualObesosList.add(percentualObesos);
	    resultadoFinal.put("percentualObesos", percentualObesosList);

	    List<Object> mediaIdadePorTipoSanguineo = doadorService.calcularMediaIdadePorTipoSanguineo(doadores);
	    resultadoFinal.put("mediaIdadePorTipoSanguineo", mediaIdadePorTipoSanguineo);

	    List<Object> quantidadeDoadoresPorTipoSanguineo = doadorService.calcularQuantidadeDoadoresPorTipoSanguineoReceptor(doadores);
	    resultadoFinal.put("quantidadeDoadoresPorTipoSanguineo", quantidadeDoadoresPorTipoSanguineo);

	    return ResponseEntity.ok(resultadoFinal);
	}

}


