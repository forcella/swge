package bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.PieChartModel;

import controller.ControleEstoque;
import controller.ControleRelatorio;
import model.Conta;
import model.Estoque;
import model.ItemVenda;
import model.Produto;

@ManagedBean
@ViewScoped
public class BeanRelatorio {
	
	private Double lucro;
	private Double gasto;
	private Double totalPago;
	private Double totalAPagar;
	private List<Conta> contasFechadas;
	private List<Conta> contasAbertas;
	private Date dataIni;
	private Date dataFim;
	private ControleRelatorio controleRelatorio;
	private ControleEstoque controleEstoque;
	private List<Estoque> intervaloEstoque;
	private boolean painelContas;
	private boolean painelMaisVendidos;
	private boolean painelProdutoEstoque;
	private List<ItemVenda> itensMaisVendidos;
	private PieChartModel grafItensMaisVendidos;
	private int qtdBusca;
	private List<Produto> listaProdutosEstoque;
	
	public BeanRelatorio() {
		controleRelatorio = new ControleRelatorio();
		controleEstoque = new ControleEstoque();
		lucro = 0d;
		gasto = 0d;
		totalPago = 0d;
		totalAPagar = 0d;
		contasFechadas = new ArrayList<Conta>();
		contasAbertas = new ArrayList<Conta>();
		dataIni = new Date();
		dataFim = new Date();
		painelContas = false;
		painelMaisVendidos = false;
		painelProdutoEstoque = false;
		itensMaisVendidos = new ArrayList<ItemVenda>();
		qtdBusca = 0;
	}
	
	public void buscarContasIntervalo(){
		lucro =0d;
		gasto = 0d;
		totalPago =0d;
		totalAPagar = 0d;
		contasAbertas = new ArrayList<Conta>();
		contasFechadas = new ArrayList<Conta>();
		
		List<Conta> listaContas = controleRelatorio.buscarRelatoriosPorIntervalo(dataIni, dataFim);
		
		List<Estoque> listaEstoque = controleEstoque.listarTodos();
		for (Conta conta : listaContas) {
			
			if(conta.getAberta()){
				contasAbertas.add(conta);
				totalPago += conta.getTotal();
			}else if(!conta.getAberta()){
				contasFechadas.add(conta);
				totalAPagar += conta.getTotal();
			}
		for (Estoque estoque : listaEstoque) {
			if(estoque.getQuantidade() > 0 && estoque.getProduto().getControladoEstoque()){
				gasto += estoque.getValorPago() * estoque.getQuantidade();
			}
		}
		
		}
	}
	
	public void maisVendidosSempre(){
		itensMaisVendidos = controleRelatorio.maisVendidosSempre();
		for(ItemVenda item: itensMaisVendidos){
			item.setValorParcial(item.getProduto().getValorVenda()*item.getQuantidade());
		}
		painelMaisVendidos = true;
		gerarGrafico(itensMaisVendidos);
	}
	
	public void maisVendidos(){
		itensMaisVendidos = controleRelatorio.maisVendidos(dataIni,dataFim);
		for(ItemVenda item: itensMaisVendidos){
			item.setValorParcial(item.getProduto().getValorVenda()*item.getQuantidade());
		}
		painelMaisVendidos = true;
		gerarGrafico(itensMaisVendidos);
	}
	
	public void gerarGrafico(List<ItemVenda> itens){
		
		grafItensMaisVendidos = new PieChartModel();
		int totalOutros = 0;
		int control = 1;
		for(ItemVenda item: itens){
			if(control<=5)
				grafItensMaisVendidos.set(item.getProduto().getNome(), item.getQuantidade());
			else
				totalOutros += item.getQuantidade();
		}
		if(totalOutros != 0)
		grafItensMaisVendidos.set("Outros",totalOutros);
	    grafItensMaisVendidos.setTitle("Grafico de Item Mais Consumido");
	    grafItensMaisVendidos.setLegendPosition("w");
	  
	}
	
	public void buscarContasPeriodo(){
		lucro =0d;
		gasto = 0d;
		totalPago =0d;
		totalAPagar = 0d;
		contasAbertas = new ArrayList<Conta>();
		contasFechadas = new ArrayList<Conta>();
		
		List<Conta> listaContas = controleRelatorio.buscarRelatoriosPorIntervaloTodasContas(dataIni, dataFim);
		
		List<Estoque> listaEstoque = controleEstoque.listarTodos();
		for (Conta conta : listaContas) {
			
			if(conta.getAberta()){
				contasAbertas.add(conta);
				totalAPagar += conta.getTotal();
			}else if(!conta.getAberta()){
				contasFechadas.add(conta);
				totalPago += conta.getTotal();
			}
		}
		painelContas = true;
	}

	

	public void inicializarPagina(){
		lucro =0d;
		gasto = 0d;
		totalPago =0d;
		totalAPagar = 0d;
		contasAbertas = new ArrayList<Conta>();
		contasFechadas = new ArrayList<Conta>();
		painelContas = false;
		dataIni = new Date();
		dataFim = new Date();
		List<Conta> listaContas = controleRelatorio.buscarRelatoriosPorIntervaloTodasContas(dataIni, dataFim);
		
		List<Estoque> listaEstoque = controleEstoque.listarTodos();
		for (Conta conta : listaContas) {
			
			if(conta.getAberta()){
				System.out.println(conta.getTotal());
				totalAPagar += conta.getTotal();
				contasAbertas.add(conta);
				
			}else if(!conta.getAberta()){
				contasFechadas.add(conta);
				totalPago += conta.getTotal();
			}
		}
	}
	
	
	
	public void buscarEstoqueFiltro(){
		listaProdutosEstoque = controleRelatorio.buscarProdutoEstoqueFiltro(qtdBusca);
		qtdBusca = 0;
		painelProdutoEstoque = true;
	}
	
	
	
	public void buscarTodoEstoque(){
		listaProdutosEstoque = controleRelatorio.buscarProtudosEstoque();
		painelProdutoEstoque = true;
		qtdBusca = 0;
	}

	public List<ItemVenda> getItensMaisVendidos() {
		return itensMaisVendidos;
	}

	public void setItensMaisVendidos(List<ItemVenda> itensMaisVendidos) {
		this.itensMaisVendidos = itensMaisVendidos;
	}

	
	
	public Double getLucro() {
		return lucro;
	}

	public void setLucro(Double lucro) {
		this.lucro = lucro;
	}

	public PieChartModel getGrafItensMaisVendidos() {
		return grafItensMaisVendidos;
	}

	public void setGrafItensMaisVendidos(PieChartModel grafItensMaisVendidos) {
		this.grafItensMaisVendidos = grafItensMaisVendidos;
	}

	public Double getGasto() {
		return gasto;
	}

	public void setGasto(Double gasto) {
		this.gasto = gasto;
	}

	public Double getTotalPago() {
		return totalPago;
	}

	public void setTotalPago(Double totalPago) {
		this.totalPago = totalPago;
	}

	public Double getTotalAPagar() {
		return totalAPagar;
	}

	public void setTotalAPagar(Double totalAPagar) {
		this.totalAPagar = totalAPagar;
	}

	public List<Conta> getContasFechadas() {
		return contasFechadas;
	}

	public void setContasFechadas(List<Conta> contasFechadas) {
		this.contasFechadas = contasFechadas;
	}

	public boolean isPainelMaisVendidos() {
		return painelMaisVendidos;
	}

	public void setPainelMaisVendidos(boolean painelMaisVendidos) {
		this.painelMaisVendidos = painelMaisVendidos;
	}

	public List<Conta> getContasAbertas() {
		return contasAbertas;
	}

	public void setContasAbertas(List<Conta> contasAbertas) {
		this.contasAbertas = contasAbertas;
	}

	public Date getDataIni() {
		return dataIni;
	}

	public void setDataIni(Date dataIni) {
		this.dataIni = dataIni;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public List<Estoque> getIntervaloEstoque() {
		return intervaloEstoque;
	}

	public void setIntervaloEstoque(List<Estoque> intervaloEstoque) {
		this.intervaloEstoque = intervaloEstoque;
	}

	public boolean isPainelContas() {
		return painelContas;
	}

	public void setPainelContas(boolean painelContas) {
		this.painelContas = painelContas;
	}

	public void inicializarPaginaConsumo() {
		painelMaisVendidos = false;
		dataIni = new Date();
		dataFim = new Date();
	}

	public void inicializarPaginaREstoque() {
		painelProdutoEstoque = false;
	}

	public int getQtdBusca() {
		return qtdBusca;
	}

	public void setQtdBusca(int qtdBusca) {
		this.qtdBusca = qtdBusca;
	}

	public List<Produto> getListaProdutosEstoque() {
		return listaProdutosEstoque;
	}

	public void setListaProdutosEstoque(List<Produto> listaProdutosEstoque) {
		this.listaProdutosEstoque = listaProdutosEstoque;
	}

	public boolean isPainelProdutoEstoque() {
		return painelProdutoEstoque;
	}

	public void setPainelProdutoEstoque(boolean painelProdutoEstoque) {
		this.painelProdutoEstoque = painelProdutoEstoque;
	}
	
	
	
}
