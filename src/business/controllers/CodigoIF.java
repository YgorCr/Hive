package business.controllers;

public interface CodigoIF {
	public abstract String geraCodigo();
	public abstract String geraQRCode(String codigo);
}
