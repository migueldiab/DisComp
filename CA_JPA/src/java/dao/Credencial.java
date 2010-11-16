package dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author migueldiab
 */
@Entity
public class Credencial {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private String login;
  private String password;
  private Rol rol;
  @OneToOne
  private Usuario usuario;

  /**
   * @return the login
   */
  public String getLogin() {
    return login;
  }

  /**
   * @param login the login to set
   */
  public void setLogin(String login) {
    this.login = login;
  }

  /**
   * @return the password
   */
  public String getPassword() {
    return password;
  }

  /**
   * @param password the password to set
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * @return the rol
   */
  public Rol getRol() {
    return rol;
  }

  /**
   * @param rol the rol to set
   */
  public void setRol(Rol rol) {
    this.rol = rol;
  }

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

  public enum Rol {
    ADMINISTRADOR, USUARIO
  }
}
