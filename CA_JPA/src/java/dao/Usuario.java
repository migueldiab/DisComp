package dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author migueldiab
 */
@Entity
public class Usuario implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  @OneToOne(mappedBy = "usuario")
  private Credencial credencial;
  private String nombre;
  private String apellido;
  @Temporal(javax.persistence.TemporalType.DATE)
  private Date fechaDeNacimiento;
  @OneToMany(mappedBy = "usuario")
  private List<Contacto> contactos;

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
   * @return the credencial
   */
  public Credencial getCredencial() {
    return credencial;
  }

  /**
   * @param credencial the credencial to set
   */
  public void setCredencial(Credencial credencial) {
    this.credencial = credencial;
  }

  /**
   * @return the nombre
   */
  public String getNombre() {
    return nombre;
  }

  /**
   * @param nombre the nombre to set
   */
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  /**
   * @return the apellido
   */
  public String getApellido() {
    return apellido;
  }

  /**
   * @param apellido the apellido to set
   */
  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  /**
   * @return the fechaDeNacimiento
   */
  public Date getFechaDeNacimiento() {
    return fechaDeNacimiento;
  }

  /**
   * @param fechaDeNacimiento the fechaDeNacimiento to set
   */
  public void setFechaDeNacimiento(Date fechaDeNacimiento) {
    this.fechaDeNacimiento = fechaDeNacimiento;
  }

  /**
   * @return the contactos
   */
  public List<Contacto> getContactos() {
    return contactos;
  }

  /**
   * @param contactos the contactos to set
   */
  public void setContactos(List<Contacto> contactos) {
    this.contactos = contactos;
  }

}
