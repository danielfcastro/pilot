package br.com.azulseguros.restpiloto.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the participante database table.
 * 
 */
@Entity
@NamedQuery(name="Participante.findAll", query="SELECT p FROM Participante p")
public class Participante implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idParticipante;

	private int codigoOrigemCalculo;

	private int corretor;

	private short idProjeto;

	private int orgao;

	public Participante() {
	}

	public int getIdParticipante() {
		return this.idParticipante;
	}

	public void setIdParticipante(int idParticipante) {
		this.idParticipante = idParticipante;
	}

	public int getCodigoOrigemCalculo() {
		return this.codigoOrigemCalculo;
	}

	public void setCodigoOrigemCalculo(int codigoOrigemCalculo) {
		this.codigoOrigemCalculo = codigoOrigemCalculo;
	}

	public int getCorretor() {
		return this.corretor;
	}

	public void setCorretor(int corretor) {
		this.corretor = corretor;
	}

	public short getIdProjeto() {
		return this.idProjeto;
	}

	public void setIdProjeto(short idProjeto) {
		this.idProjeto = idProjeto;
	}

	public int getOrgao() {
		return this.orgao;
	}

	public void setOrgao(int orgao) {
		this.orgao = orgao;
	}

}