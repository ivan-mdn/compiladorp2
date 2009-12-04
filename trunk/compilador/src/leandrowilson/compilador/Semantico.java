package leandrowilson.compilador;

import javax.swing.text.Position;

public class Semantico {
	Integer contadorEscopo =0;
	List erros = new List();
	StringBuffer codigo = new StringBuffer();
	Integer inicioAreaDeDados = 1000;
	Integer ponteiroAreaDeDados = inicioAreaDeDados-1;
	List reservasDeMemoria = new List();
	String strI ="(lambda(x)x)";
	String strK = "(lambda(x y)x)";
	String strS = "(lambda(x y z)((x z) (y z)))";
	
	public ElementoSemantico analisa(ElementoSemantico elSemantico) {
		Escopo escopo = elSemantico.escopo;
		TipoMaquina tipoMaquina = elSemantico.tipoMaquina;
		Integer transicaoSemantica =elSemantico.transicaoSemantica;
		PilhaSemantico pilhaSemantico = elSemantico.pilhaSemantico;
		Token token = elSemantico.token;
		Token tempToken;
		if(transicaoSemantica==-1){
			return elSemantico;
		}
		
		switch (tipoMaquina) {
			case ASTIOTA:
				switch (transicaoSemantica) {
				case 0: //(0,"*")->1
					break;
				case 1: //(1,IotaExpr)->2
					break;
				case 2: //(2,IotaExpr)->3
					break;
				default:
					erros.add(new Erro(TipoErro.SEMANTICO,elSemantico.token));
					break;
				}
				break;
			case EXPR:
				switch (transicaoSemantica) {
				case 0: //(0, "i") -> 1
					gerdaCodigoI();
					break;
				case 1: //(0, Expr_) -> 1
					break;
				default:
					break;
				}
				break;
			case EXPR2:
				switch (transicaoSemantica) {
				case 0: //(0, "I") -> 1
					geraCodigoI();
					break;
				case 1: //(0, "K") -> 1
					geraCodigoK();
					break;
				case 2: //(0, "k") -> 1
					geraCodigoK();
					break;
				case 3: //(0, "S") -> 1
					geraCodigoS();
					break;
				case 4: //(0, "s") -> 1
					geraCodigoS();
					break;
				case 5: //(0, NonemptyJotExpr) -> 1
					break;
				case 6: //(0, quoteExp) -> 1
					break;
				case 7: //(0, IotaExpr) -> 1
					break;
				case 8: //(0, "(") -> 2
					geraCodigo_ABRE_PAR();
					break;
				case 9: //(2, Expr) -> 3
					break;
				case 10: //(3, ")") -> 1
					geraCodigo_FECHA_PAR();
					break;

				default:
					break;
				}
				break;
			case IOTAEXPR:
				switch (transicaoSemantica) {
				case 0: //(0, "i") -> 1
					geraCodigoIota_i();
					break;
				case 1: //(0, Expr_) -> 1
					break;
				default:
					break;
				}
				break;
			case NONEMPTYJOTEXPR:
				String code = codigo.toString();
				if(code.contains("$")){
					switch (transicaoSemantica) {
					case 0: //(0, "0") -> 1
						codigo = new StringBuffer(code.replaceAll("$","($ "+strS+" "+ strK + ")" ));
						break;
					case 1: //(0, "1") -> 1
						codigo = new StringBuffer(code.replaceAll("$","(lambda(x y) ($ (x y)))"));
						break;
					case 2: //(1, "0") -> 1
						codigo = new StringBuffer(code.replaceAll("$","($ "+strS+" "+ strK + ")" ));
						break;
					case 3: //(1, "1") -> 1
						codigo = new StringBuffer(code.replaceAll("$","(lambda(x y) ($ (x y)))"));
						break;
					default:
						break;
					}
				}
				else{
					switch (transicaoSemantica) {
					case 0: //(0, "0") -> 1
						codigo.append("($ "+strS+" "+ strK + ")" );
						break;
					case 1: //(0, "1") -> 1
						codigo.append("(lambda(x y) ($ (x y)))");
						break;
					case 2: //(1, "0") -> 1
						codigo.append("($ "+strS+" "+ strK + ")" );
						break;
					case 3: //(1, "1") -> 1
						codigo.append("(lambda(x y) ($ (x y)))");
						break;
					default:
						break;
					}
				}				
				break;
			case PROGRAM:
				switch (transicaoSemantica) {
				case 999990: //(0,LAST)->1;
					//epsilon
				case 0: //(0, Expr) -> 1
					break;
				case 1: //(1, Expr) -> 1
					break;
				default:
						break;
				}
				break;
			case QUOTEEXPR:
				switch (transicaoSemantica) {
				case 0: //(0, "`") -> 1
					break;
				case 1: //(1, Expr) -> 2
					break;
				case 2: //(2, Expr) -> 3
					break;
				default:
					break;
				}
				break;
		}
		return elSemantico;
	}

	private void geraCodigo_FECHA_PAR() {
		codigo.append(")");
	}

	private void geraCodigo_ABRE_PAR() {
		codigo.append("(");
		
	}

	private void geraCodigoS() {
	codigo.append(strS);
		
	}

	private void geraCodigoK() {
		codigo.append(strK);
		
	}

	private void geraCodigoI() {
		codigo.append(strI);
		
	}

	private void geraCodigoIota_i() {
		codigo.append("(lambda(x)(x "+strS+strK+"))");
		
	}

	private void gerdaCodigoI() {
		codigo.append(strI);
		
	}

	private void erroSemantico_redeclaracaoDeVariavel(Token token) {
		erros.add(new Erro(TipoErro.SEMANTICO_REDECALRACAO_DE_VARIAVEL,token));
		Util.Log("ErroSemantico-RedeclaracaoDe Variavel");
		
	}


	private void analisaToken(Token lido, TipoToken esperado) {
		if (!lido.tipo.equals(esperado)){
			erroSemantico(lido);
		}
	}

	private void erroSemantico(Token token) {
		erros.add(new Erro(TipoErro.SEMANTICO,token));
		Util.Log("ErroSemantico");	
	}

	private void geraCodigo_inicial() {

	}

	private void logCodigo() {
		Util.Log(codigo.toString());
		
	}

	public void geraArquivo() {
		codigo.append(geraCodigoAreaDeDados());
		String finalCode = codigo.toString();
		Util.Log("Código final Gerado:\n"+ finalCode);
	}

	private String geraCodigoAreaDeDados() {
		StringBuffer code = new StringBuffer();
		code.append("\n&/");
		code.append(inicioAreaDeDados.toString()+"\n");
		
		for (int i=0;i<reservasDeMemoria.tamanho;i++){
			code.append((String)reservasDeMemoria.get(i)+" K /0000\n");
		}
		return code.toString();
	}

}
