package com.sofis.web.presentacion.tipos;

public class Seleccionable<T> {

	private T objeto;
	private boolean seleccionado;

	public Seleccionable() {
	}

	public Seleccionable(T objeto) {
		this.objeto = objeto;
	}

	public T getObjeto() {
		return objeto;
	}

	public void setObjeto(T objeto) {
		this.objeto = objeto;
	}

	public boolean isSeleccionado() {
		return seleccionado;
	}

	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}

}
