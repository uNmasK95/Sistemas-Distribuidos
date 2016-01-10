package teste2015;

public interface leilaoInterface {
	public static String anunciar = "ANUNCIAR";
	public static String listar = "LISTAR";
	public static String oferecer = "OFERECER";
	
	public String anunciar(String item, double valor);
	public String listar();
	public String oferecer(String item, double valor);
}
