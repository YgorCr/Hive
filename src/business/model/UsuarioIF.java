/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package business.model;

/**
 *
 * @author ygor
 */
public interface UsuarioIF{
    public Long getId();
    public void setId(Long id);
    public String getNome();
    public void setNome(String nome);
    public String getEmail();
    public void setEmail(String email);
    public void setIdade(Integer idade);
    public Integer getIdade();
}
