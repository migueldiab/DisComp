package dao;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author migueldiab
 */
@Entity
public class Contacto {
  @ManyToOne
  private Usuario usuario;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private String nombre;
  private String apellido;
  private String telefono;
  private String movil;
  private String email;
  @OneToMany(mappedBy = "contacto")
  private List<Direccion> direcciones;

  /**
   * @return the usuario
   */
  public Usuario getUsuario() {
    return usuario;
  }

  /**
   * @param usuario the usuario to set
   */
  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

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
   * @return the telefono
   */
  public String getTelefono() {
    return telefono;
  }

  /**
   * @param telefono the telefono to set
   */
  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  /**
   * @return the movil
   */
  public String getMovil() {
    return movil;
  }

  /**
   * @param movil the movil to set
   */
  public void setMovil(String movil) {
    this.movil = movil;
  }

  /**
   * @return the email
   */
  public String getEmail() {
    return email;
  }

  /**
   * @param email the email to set
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * @return the direcciones
   */
  public List<Direccion> getDirecciones() {
    return direcciones;
  }

  /**
   * @param direcciones the direcciones to set
   */
  public void setDirecciones(List<Direccion> direcciones) {
    this.direcciones = direcciones;
  }

  
}
