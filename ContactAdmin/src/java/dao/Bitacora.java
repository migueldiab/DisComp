package dao;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * @author migueldiab
 */
@Entity
public class Bitacora {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  @Temporal(javax.persistence.TemporalType.DATE)
  private Date fechaHora;
  private String tipoAccion;

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
   * @return the fechaHora
   */
  public Date getFechaHora() {
    return fechaHora;
  }

  /**
   * @param fechaHora the fechaHora to set
   */
  public void setFechaHora(Date fechaHora) {
    this.fechaHora = fechaHora;
  }

  /**
   * @return the tipoAccion
   */
  public String getTipoAccion() {
    return tipoAccion;
  }

  /**
   * @param tipoAccion the tipoAccion to set
   */
  public void setTipoAccion(String tipoAccion) {
    this.tipoAccion = tipoAccion;
  }

  
}
