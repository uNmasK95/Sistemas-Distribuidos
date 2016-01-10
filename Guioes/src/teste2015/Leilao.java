package teste2015;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class Leilao implements leilaoInterface{

	private Map<String,Item> lista;
	private ReentrantLock lock;
	
	public Leilao() {
		lista = new HashMap<>();
		lock = new ReentrantLock();
	}

	@Override
	public String anunciar(String item, double valor) {
		String res = "";
		lock.unlock();
		Item t = new Item(item,valor,lock);
		lista.put(item,t);
		try {
			while(t.getValorProposto()<t.getValor() && t.getPropostas()<5){
				t.getItemAnunciado().await();	
			}
			if(t.getPropostas()<5){
				res = "item vendido";
			}else{
				res = "item retirado";
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			lock.lock();
		}
		return res;
	}

	@Override
	public String listar() {
		lock.lock();
		StringBuilder ap = new StringBuilder();
		for(Item t : lista.values()){
			ap.append(t.toString());
			ap.append(";");
		}
		lock.unlock();
		return ap.toString();
	}

	@Override
	public String oferecer(String item, double valor) {
		String res = "";
		lock.lock();
		Item t = lista.get(item);
		if(t.getValor()<valor){
			t.setValorProposto(valor);
			t.getItemAnunciado().signal();
			res = "comprado";
			
		}else{
			t.setValorProposto(valor);
			t.addproposta();
			if(t.getPropostas()>=5){
				t.getItemAnunciado().signal();
			}
			res = "insuficiente";
		}
		lock.unlock();
		return res;
	}
	
	

}
