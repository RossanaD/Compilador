import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Semantico<T> implements Constants {
	// registros semânticos - atributos do analisador semântico
	// operador (inicialmente igual a ""): usado para armazenar o operador
	// relacional reconhecido
	// pela ação #9, para uso posterior na ação #10.
	String operador = "";
	// código: usado para armazenar o código objeto gerado.
	private ArrayList<String> codigo = new ArrayList<>();
	// pilha_tipos (inicialmente vazia): usada para determinar o tipo de uma
	// expressão durante a
	// compilação do programa, podendo ser: ....
	Stack<String> pilha_tipos = new Stack<>();

	String identificador = "";

	ArrayList<String> lista_id = new ArrayList<>();

	Stack<String> pilha_rotulos = new Stack<>();
	
	List<String> tabela_simbolos = new ArrayList<>();
	
	int incremento = 0;
	
	public ArrayList<String> getCodigo(){
		return codigo;
	}
	
	public void executeAction(int action, Token token, int linha) throws SemanticError {
//		System.out.println("Ação #"+action+", Token: "+token);
		switch (action) {
		case 1:
			acao01(linha);
			break;
		case 2:
			acao02(linha);
			break;
		case 3:
			acao03(linha);
			break;
		case 4:
			acao04(linha);
			break;
		case 5:
			acao05(token);
			break;
		case 6:
			acao06(token);
			break;
		case 7:
			acao07(linha);
			break;
		case 8:
			acao08(linha);
			break;
		case 9:
			acao09(token);
			break;
		case 10:
			acao10(linha);
			break;
		case 11:
			acao11();
			break;
		case 12:
			acao12();
			break;
		case 13:
			acao13(linha);
			break;
		case 14:
			acao14();
			break;
		case 15:
			acao15();
			break;
		case 16:
			acao16();
			break;
		case 17:
			acao17(token);
			break;
		case 18:
			acao18(linha);
			break;
		case 19:
			acao19(linha);
			break;
		case 20:
			acao20(token);
			break;
		case 21:
			acao21(token, linha);
			break;
		case 22:
			acao22(token, linha);
			break;
		case 23:
			acao23();
			break;
		case 24:
			acao24(linha);
			break;
		case 25:
			acao25(token);
			break;
		case 26:
			acao26();
			break;
		case 27:
			acao27();
			break;
		case 28:
			acao28();
			break;
		case 29:
			acao29();
			break;
		case 30:
			acao30();
			break;
		case 31:
			acao31();
			break;
		case 32:
			acao32();
			break;
		case 33:
			acao33(token, linha);
			break;
		default:
			throw new SemanticError("Ação semântica não implementada: " + action);
		}
	}

	// acoes semanticas: um método para cada acao do esquema de traducao
	private void acao01(int linha) throws SemanticError {
		// implementar
		// tipo1:= pilha_tipos.desempilha
		// tipo2:= pilha_tipos.desempilha
		String tipo1 = pilha_tipos.pop();
		String tipo2 = pilha_tipos.pop();
		// validar tipo1 e tipo2, ambos devem ser int64 ou float64
		if (tipo1 != tipo2) {
			throw new SemanticError("tipos incompatíveis em expressão aritmética", linha);
		}
		if (tipo1.equals("float64") || tipo2.equals("float64")) {
			pilha_tipos.push("float64");
		} else {
			pilha_tipos.push("int64");
		}
		codigo.add("add\n");
	}

	private void acao02(int linha) throws SemanticError {
		String tipo1 = pilha_tipos.pop();
		String tipo2 = pilha_tipos.pop();
		if (tipo1 != tipo2) {
			throw new SemanticError("tipos incompatíveis em expressão aritmética", linha);
		}
		if (tipo1.equals("float64") || tipo2.equals("float64")) {
			pilha_tipos.push("float64");
		} else {
			pilha_tipos.push("int64");
		}
		codigo.add("sub\n");
	}

	private void acao03(int linha) throws SemanticError {
		String tipo1 = pilha_tipos.pop();
		String tipo2 = pilha_tipos.pop();		
		if((tipo1.equals("string") || tipo1.equals("char")) 
				|| (tipo2.equals("string") || tipo2.equals("char"))) {
			throw new SemanticError("tipos incompatíveis em expressão aritmética", linha);
		}
		if (tipo1.equals("float64") || tipo2.equals("float64")) {
			pilha_tipos.push("float64");
		} else {
			pilha_tipos.push("int64");
		}
		codigo.add("mul\n");
	}

	private void acao04(int linha) throws SemanticError {
		String tipo1 = pilha_tipos.pop();
		String tipo2 = pilha_tipos.pop();
		if (tipo1 == tipo2) {
			pilha_tipos.push(tipo1);
		} else {
			throw new SemanticError("tipos incompatíveis em expressão aritmética", linha);
		}
		codigo.add("div\n");
	}

	private void acao05(Token token) {
		pilha_tipos.push("int64");
		codigo.add("ldc.i8 " + token.getLexeme().replace("_", "") + "\n");
		codigo.add("conv.r8\n");

	}

	private void acao06(Token token) {
		pilha_tipos.push("float64");
		codigo.add("ldc.r8 " + token.getLexeme().replace("_", "") + "\n");
	}

	private void acao07(int linha) throws SemanticError {
		String tipo1 = pilha_tipos.pop();
		if (tipo1.equals("float64") || tipo1.equals("int64")) {
			pilha_tipos.push(tipo1);
		} else {
			throw new SemanticError("tipo incompatível em expressão aritmética", linha);
		}
	}

	private void acao08(int linha) throws SemanticError {
		String tipo1 = pilha_tipos.pop();
		if (tipo1.equals("float64") || tipo1.equals("int64")) {
			pilha_tipos.push(tipo1);
		} else {
			throw new SemanticError("tipo incompatível em expressão aritmética", linha);
		}

		codigo.add("ldc.i8 -1\n");
		codigo.add("conv.r8\n");
		codigo.add("mul\n");
	}

	private void acao09(Token token) {
		operador = token.getLexeme();
	}

	private void acao10(int linha) throws SemanticError {
		String tipo1 = pilha_tipos.pop();
		String tipo2 = pilha_tipos.pop();
		if((tipo1.equals("int64") && tipo2.equals("int64")) 
				|| (tipo1.equals("float64") && tipo2.equals("float64"))
				|| (tipo1.equals("string") && tipo2.equals("string"))
				|| (tipo1.equals("int64") && tipo2.equals("float64"))) {
			pilha_tipos.push("bool");
		}else {
			throw new SemanticError("tipos incompatíveis em expressão relacional", linha);
		}
		if(operador.equals(">")) {
			codigo.add("cgt\n");
		}else if(operador.equals("<")) {
			codigo.add("clt\n");
		}else if(operador.equals("==")) {
			codigo.add("ceq\n");
		}else if(operador.equals("!=")) {
			codigo.add("ceq\n");
		}
	}

	private void acao11() {
		pilha_tipos.push("bool");
		codigo.add("ldc.i4.1\n");
	}

	private void acao12() {
		pilha_tipos.push("bool");
		codigo.add("ldc.i4.0\n");
	}

	private void acao13(int linha) throws SemanticError {
		String tipo = pilha_tipos.pop();
		if (tipo.equals("bool")) {
			pilha_tipos.push("bool");
		} else {
			throw new SemanticError("tipo incompatível em expressão lógica", linha);
		}

		codigo.add("ldc.i4.1\n");
		codigo.add("xor\n");
	}

	private void acao14() {
		String tipo = pilha_tipos.pop();
		if(tipo.equals("int64")) {
			codigo.add("conv.i8\n");
		}
		codigo.add("call void [mscorlib]System.Console::Write(" + tipo + ")\n");
	}

	private void acao15() {
		String str = ".assembly extern mscorlib {}\n" + ".assembly _codigo_objeto {}\n"
				+ ".module _codigo_objeto.exe\n\n" + ".class public _UNICA {\n"
				+ ".method static public void _principal() {\n" + "	.entrypoint\n";
		codigo.add(str);

	}

	private void acao16() {
		String str = "ret\n" + " }\n" + "}\n";
		codigo.add(str);
	}

	private void acao17(Token token) {
		pilha_tipos.add("string");
		codigo.add("ldstr " + token.getLexeme().replace("\'", "\"") + "\n");
	}

	private void acao18(int linha) throws SemanticError {
		String tipo1 = pilha_tipos.pop();
		String tipo2 = pilha_tipos.pop();
		if(!tipo1.equals("bool") && !tipo2.equals("bool")) {
			throw new SemanticError("tipo incompatível em expressão lógica", linha);
		}
		pilha_tipos.push("bool");
		codigo.add("and\n");
	}

	private void acao19(int linha) throws SemanticError {
		String tipo1 = pilha_tipos.pop();
		String tipo2 = pilha_tipos.pop();
		if(!tipo1.equals("bool") && !tipo2.equals("bool")) {
			throw new SemanticError("tipo incompatível em expressão lógica", linha);
		}
		pilha_tipos.push("bool");
		codigo.add("or\n");
	}

	private void acao20(Token token) {
		String lexeme = token.getLexeme();
		if (lexeme.equals("\\t")) {
			codigo.add("ldstr \"" + lexeme + "\" \n");
		} else if (lexeme.equals("\\n")) {
			codigo.add("ldstr \"" + lexeme + "\" \n");
		} else {
			codigo.add("ldstr \" \" \n");
		}
		pilha_tipos.add("string");
	}

	private void acao21(Token token, int linha) throws SemanticError {
		String valor_inicial = token.getLexeme();
		String tipo = "";
		for (String id : lista_id) {
			for(String simbol : tabela_simbolos) {
				if(simbol.contains(id)) {
					throw new SemanticError(id+" já declarado", linha);
				}
			}
			if(token.getId() == 3) {
				tipo = "int64";
			}else if(token.getId() == 4) {
				tipo = "float64";
			}else if(token.getId() == 5) {
				tipo = "char";
			}else if(token.getId() == 6) {
				tipo = "string";
			}else if(valor_inicial == "true" || valor_inicial == "false") {
				tipo = "bool";
			}
			tabela_simbolos.add(id+";"+tipo);
			codigo.add(".locals ("+tipo+" "+id+")\n");
			if(tipo.equals("int64")) {
				codigo.add("ldc.i8 "+valor_inicial.replace("_", "")+"\n");
			}
			if(tipo.equals("float64")) {
				codigo.add("ldc.r8 "+valor_inicial.replace("_","")+"\n");
			}
			if(tipo.equals("char")) {
				if(valor_inicial.equals("\\t")) {
					codigo.add("ldstr \"" + valor_inicial + "\" \n");
				}else if (valor_inicial.equals("\\n")) {
					codigo.add("ldstr \"" + valor_inicial + "\" \n");
				}else {
					codigo.add("ldstr \" \" \n");
				}				
			}
			if(tipo.equals("string")) {
				codigo.add("ldstr "+valor_inicial.replace("\'", "\"")+"\n");
			}
			if(tipo.equals("bool") && valor_inicial.equals("true")) {
				codigo.add("ldc.i4.1\n");
			}
			if(tipo.equals("bool") && valor_inicial.equals("false")) {
				codigo.add("ldc.i4.0\n");
			}
			
			codigo.add("stloc " + id + "\n");
		}
		
		lista_id.clear();
	}

	private void acao22(Token token, int linha) throws SemanticError {
		String id = token.getLexeme();
		boolean existe = false;
		for (String simbolos : tabela_simbolos) {
			if(simbolos.contains(id)) {
				existe = true;
				break;
			}
		}
		if(existe == false)
			throw new SemanticError(id+" não declarado", linha);
		
		identificador = id;
	}

	private void acao23() {
		String tipo = "";
		for (String simbolos : tabela_simbolos) {
			if(simbolos.contains(identificador)) {
				String[] array = simbolos.split(";");
				tipo = array[1];
				break;
			}
		}
		
		if(tipo.equals("int64")) {
			codigo.add("conv.i8\n");
		}
		codigo.add("stloc "+identificador+"\n");
	}

	private void acao24(int linha) throws SemanticError {
		boolean achou = false;
		String tipo = "";
		for(String id : lista_id) {
			for(String simbol : tabela_simbolos) {
				if(simbol.contains(id)) {
					achou = true;
					String[] array = simbol.split(";");
					tipo = array[1];
					break;
				}
			}
			if(achou == false)
				throw new SemanticError(id+" não declarado", linha);
			
			codigo.add("call string [mscorlib]System.Console::ReadLine()\n");
			if(tipo.equals("int64")) {
				codigo.add("call int64 [mscorlib]System.Int64::Parse(string)\n");
			}else if(tipo.equals("float64")) {
				codigo.add("call float64 [mscorlib]System.Double::Parse(string)\n");
			}else if(tipo.equals("bool")) {
				codigo.add("call bool [mscorlib]System.Boolean::Parse(string)\n");
			}
			codigo.add("stloc "+id+"\n");
		}
	}

	private void acao25(Token token) {
		lista_id.add(token.getLexeme());
	}

	private void acao26() {
		incremento++;
		String rotulo = "r"+incremento;
		pilha_rotulos.push(rotulo);
		codigo.add("brfalse r"+incremento+"\n");
	}

	private void acao27() {
		incremento++;
		String rotulo = "r"+incremento;
		codigo.add("br "+rotulo+"\n");
		codigo.add(pilha_rotulos.pop()+":\n");
		pilha_rotulos.push(rotulo);
	}

	private void acao28() {
		String rotulo = pilha_rotulos.pop();
		codigo.add(rotulo+":\n");
	}

	private void acao29() {
		incremento++;
		String rotulo = "r"+incremento;
		pilha_rotulos.push(rotulo);
		codigo.add(rotulo+":\n");
	}

	private void acao30() {
		incremento++;
		String rotulo = "r"+incremento;
		codigo.add("brfalse "+rotulo+"\n");
		pilha_rotulos.add(rotulo);
	}

	private void acao31() {
		String rotulo1 = pilha_rotulos.pop();
		String rotulo2 = pilha_rotulos.pop();
		codigo.add("br "+rotulo2+"\n");
		codigo.add(rotulo1+":\n");
	}

	private void acao32() {
		incremento++;
		String rotulo = "r"+incremento;
		codigo.add("brtrue "+rotulo+"\n");
		pilha_rotulos.push(rotulo);
	}

	private void acao33(Token token, int linha) throws SemanticError {
		String id = token.getLexeme();
		String tipo = "";
		boolean achou = false;
		for(String simbol : tabela_simbolos) {
			if(simbol.contains(id)) {
				achou = true;
				String[] array = simbol.split(";");
				tipo = array[1];
				break;
			}
		}
		if(achou == false)
			throw new SemanticError(id+" não declarado", linha);
		
		codigo.add("ldloc "+id+"\n");
		pilha_tipos.add(tipo);
		if(tipo.equals("int64")) {
			codigo.add("conv.r8\n");
		}		
	}
}
