/*
 * Padrão: AbstractFactory
 */

package infra;

public class DaoAbstractFactory {
	public static DaoIF<?> getInstance(Class<?> classe){
		String classeName = classe.getName().split("\\.")[classe.getName().split("\\.").length-1];
		
		switch(classeName){
			case "UsuarioAB":
				return DaoUsuario.getInstance();
			case "IngressoAB":
				return DaoIngresso.getInstance();
			case "EventoAB":
				return DaoEvento.getInstance();
		}
		
		return null;
	}
}
