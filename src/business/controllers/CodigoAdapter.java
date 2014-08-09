package business.controllers;

public class CodigoAdapter implements CodigoIF{
	public String geraCodigo(){
		return FuncoesStaticas.geraCodigo();	
	}
	
	public String geraQRCode(String codigo){    //retorna o caminho relativo onde o QRcode foi gerado
		return FuncoesStaticas.geraQRCode(codigo);
     
	}
}
