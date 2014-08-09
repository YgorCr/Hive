package infra;

public class DaoAbstractFactory {
	public static DaoIF<?> getInstance(Class<?> classe){
		switch(classe.getName()){
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
