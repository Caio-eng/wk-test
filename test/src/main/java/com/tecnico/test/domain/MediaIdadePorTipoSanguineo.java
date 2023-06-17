package com.tecnico.test.domain;

import java.io.Serializable;

public class MediaIdadePorTipoSanguineo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String tipoSanguineo;
	private double mediaIdade;

	public MediaIdadePorTipoSanguineo() {
		super();
	}

	public MediaIdadePorTipoSanguineo(String tipoSanguineo, double mediaIdade) {
		super();
		this.tipoSanguineo = tipoSanguineo;
		this.mediaIdade = mediaIdade;
	}

	public String getTipoSanguineo() {
		return tipoSanguineo;
	}

	public void setTipoSanguineo(String tipoSanguineo) {
		this.tipoSanguineo = tipoSanguineo;
	}

	public double getMediaIdade() {
		return mediaIdade;
	}

	public void setMediaIdade(double mediaIdade) {
		this.mediaIdade = mediaIdade;
	}

}
