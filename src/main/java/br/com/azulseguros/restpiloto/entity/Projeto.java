package br.com.azulseguros.restpiloto.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the projeto database table.
 * 
 */
@Entity
@NamedQueries({ 
	@NamedQuery(name="Projeto.findAll", query="SELECT p FROM Projeto p"),
	@NamedQuery(name="Projeto.findById", query="SELECT p FROM Projeto p WHERE p.id = :id"),
	@NamedQuery(name="Projeto.findByStatus", query="SELECT p FROM Projeto p WHERE p.status = :status"),
	@NamedQuery(name="Projeto.findByName", query="SELECT p FROM Projeto p WHERE p.descricao LIKE :descricao")
})
public class Projeto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private short id;

	private String descricao;

	private byte status;

	public Projeto() {
	}

	public short getId() {
		return this.id;
	}

	public void setId(short id) {
		this.id = id;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

}