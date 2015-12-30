package es.uvigo.esei.dgss.exercises.web;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean(name = "myController")
@SessionScoped
public class MyController {
	private String campo1;
	private String campo2;
	private Date fecha;

	public String getCampo1() {
		return campo1;

	}

	public void setCampo1(String campo1) {
		this.campo1 = campo1;
	}

	public String getCampo2() {
		return campo2;
	}

	public void setCampo2(String campo2) {
		this.campo2 = campo2;
	}

	public Date getFecha() {
		return fecha;

	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}