package infra;

public class DaoAbstractFactory {
	public static DaoIF<?> getInstance(Class<?> classe){
		System.out.println(classe.getName());
		
		System.out.println(classe.getName().split(".").length);
		
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
