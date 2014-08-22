/*
 * Padrão: AbstractFactory
 */

package infra;

public class DaoAbstractFactory {
	public static DaoIF<?> getInstance(Class<?> classe){
		String classeName = classe.getName().split("\\.")[classe.getName().split("\\.").length-1];
		
		Boolean persistente = true; // Altere aqui para mudar o tipo de persistencia
		
		if (persistente) {
			switch(classeName){
				case "UsuarioAB":
					return DaoUsuario.getInstance();
				case "IngressoAB":
					return DaoIngresso.getInstance();
				case "EventoAB":
					return DaoEvento.getInstance();
			}
		} else {
			
			switch(classeName){
			case "UsuarioAB":
				return DaoUsuarioMemo.getInstance();
			case "IngressoAB":
				return DaoIngressoMemo.getInstance();
			case "EventoAB":
				return DaoEventoMemo.getInstance();
		}
		}
		return null;
	}
}
