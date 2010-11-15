package dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author migueldiab
 */
@Entity
public class Direccion {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private String calle;
  private String numero;
  @ManyToOne
  private Contacto contacto;
  private TipoDireccion tipo;

  /**
   * @return the id
   */
  public Integer getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * @return the calle
   */
  public String getCalle() {
    return calle;
  }

  /**
   * @param calle the calle to set
   */
  public void setCalle(String calle) {
    this.calle = calle;
  }

  /**
   * @return the numero
   */
  public String getNumero() {
    return numero;
  }

  /**
   * @param numero the numero to set
   */
  public void setNumero(String numero) {
    this.numero = numero;
  }

  /**
   * @return the contacto
   */
  public Contacto getContacto() {
    return contacto;
  }

  /**
   * @param contacto the contacto to set
   */
  public void setContacto(Contacto contacto) {
    this.contacto = contacto;
  }

  /**
   * @return the tipo
   */
  public TipoDireccion getTipo() {
    return tipo;
  }

  /**
   * @param tipo the tipo to set
   */
  public void setTipo(TipoDireccion tipo) {
    this.tipo = tipo;
  }

  public enum TipoDireccion {
    CASA, TRABAJO, OTRA
  }

  
}
