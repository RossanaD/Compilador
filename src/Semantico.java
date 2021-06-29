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
	List<String> codigo = new ArrayList<>();
	// pilha_tipos (inicialmente vazia): usada para determinar o tipo de uma
	// expressão durante a
	// compilação do programa, podendo ser: ....
	Stack<String> pilha_tipos = new Stack<>();

	String identificador = "";

	List<String> lista_id = new ArrayList<>();

	Stack<T> pilha_rotulos = new Stack<>();
	
	List<String> tabela_simbolos = new ArrayList<>();

	public void executeAction(int action, Token token) throws SemanticError {
//		System.out.println("Ação #"+action+", Token: "+token);
		switch (action) {
		case 1:
			acao01();
			break;
		case 2:
			acao02();
			break;
		case 3:
			acao03();
			break;
		case 4:
			acao04();
			break;
		case 5:
			acao05(token);
			break;
		case 6:
			acao06(token);
			break;
		case 7:
			acao07();
			break;
		case 8:
			acao08();
			break;
		case 9:
			acao09(token);
			break;
		case 10:
			acao10();
			break;
		case 11:
			acao11();
			break;
		case 12:
			acao12();
			break;
		case 13:
			acao13();
			break;
		case 14:
			acao14();
			break;
		case 15:
			acao15();
			break;
		case 16:
			acao16(token);
			break;
		case 17:
			acao17(token);
			break;
		case 18:
			acao18();
			break;
		case 19:
			acao19();
			break;
		case 20:
			acao20(token);
			break;
		case 21:
			acao21(token);
			break;
		case 22:
			acao22(token);
			break;
		case 23:
			acao23();
			break;
		case 24:
			acao24();
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
			acao33(token);
			break;
		default:
			throw new SemanticError("Ação semântica não implementada: " + action);
		}
	}

	// acoes semanticas: um método para cada acao do esquema de traducao
	private void acao01() throws SemanticError {
		// implementar
		// tipo1:= pilha_tipos.desempilha
		// tipo2:= pilha_tipos.desempilha
		String tipo1 = pilha_tipos.pop();
		String tipo2 = pilha_tipos.pop();
		// validar tipo1 e tipo2, ambos devem ser int64 ou float64
		if (tipo1 != tipo2) {
			throw new SemanticError("tipos incompatíveis em expressão aritmética");
		}
		if (tipo1 == "float64" || tipo2 == "float64") {
			pilha_tipos.push("float64");
		} else {
			pilha_tipos.push("int64");
		}
		codigo.add("add\n");
	}

	private void acao02() throws SemanticError {
		String tipo1 = pilha_tipos.pop();
		String tipo2 = pilha_tipos.pop();
		if (tipo1 != tipo2) {
			throw new SemanticError("tipos incompatíveis em expressão aritmética");
		}
		if (tipo1 == "float64" || tipo2 == "float64") {
			pilha_tipos.push("float64");
		} else {
			pilha_tipos.push("int64");
		}
		codigo.add("sub\n");
	}

	private void acao03() throws SemanticError {
		String tipo1 = pilha_tipos.pop();
		String tipo2 = pilha_tipos.pop();
		if (tipo1 != tipo2) {
			throw new SemanticError("tipos incompatíveis em expressão aritmética");
		}
		if (tipo1 == "float64" || tipo2 == "float64") {
			pilha_tipos.push("float64");
		} else {
			pilha_tipos.push("float64");
		}
		codigo.add("mul\n");
	}

	private void acao04() throws SemanticError {
		String tipo1 = pilha_tipos.pop();
		String tipo2 = pilha_tipos.pop();
		if (tipo1 == tipo2) {
			pilha_tipos.push(tipo1);
		} else {
			throw new SemanticError("tipos incompatíveis em expressão aritmética");
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

	private void acao07() throws SemanticError {
		String tipo1 = pilha_tipos.pop();
		if (tipo1 == "float64" || tipo1 == "int64") {
			pilha_tipos.push(tipo1);
		} else {
			throw new SemanticError("tipo incompatível em expressão aritmética");
		}
	}

	private void acao08() throws SemanticError {
		String tipo1 = pilha_tipos.pop();
		if (tipo1 == "float64" || tipo1 == "int64") {
			pilha_tipos.push(tipo1);
		} else {
			throw new SemanticError("tipo incompatível em expressão aritmética");
		}

		codigo.add("ldc.i8 -1\n");
		codigo.add("conv.r8\n");
		codigo.add("mul\n");
	}

	private void acao09(Token token) {
		operador = token.getLexeme();
	}

	private void acao10() {

	}

	private void acao11() {
		pilha_tipos.push("bool");
		codigo.add("ldc.i4.1\n");
	}

	private void acao12() {
		pilha_tipos.push("bool");
		codigo.add("ldc.i4.0\n");
	}

	private void acao13() throws SemanticError {
		String tipo = pilha_tipos.pop();
		if (tipo == "bool") {
			pilha_tipos.push("bool");
		} else {
			throw new SemanticError("tipo incompatível em expressão lógica");
		}

		codigo.add("ldc.i4.1\n");
		codigo.add("xor\n");
	}

	private void acao14() {
		String tipo = pilha_tipos.pop();
		codigo.add("call void [mscorlib]System.Console::Write(" + tipo + ")\n");
	}

	private void acao15() {
		String str = ".assembly extern mscorlib {}\n" + ".assembly _codigo_objeto {}\n"
				+ ".module _codigo_objeto.exe\n\n" + ".class public _UNICA {\n"
				+ ".method static public void _principal() {\n" + "	.entrypoint\n";
		codigo.add(str);

	}

	private void acao16(Token token) {
		String str = "	ret\n" + "	}\n" + "}";
		codigo.add(str);
	}

	private void acao17(Token token) {
		codigo.add("ldstr" + token.getLexeme().replace("\'", "\"") + "\n");
	}

	private void acao18() {

	}

	private void acao19() {

	}

	private void acao20(Token token) {
		String lexeme = token.getLexeme();
		if (lexeme == "\t") {
			codigo.add("ldstr \"" + lexeme + "\" \n");
		} else if (lexeme == "\n") {
			codigo.add("ldstr \"" + lexeme + "\" \n");
		} else {
			codigo.add("ldstr \" \" \n");
		}

	}

	private void acao21(Token token) throws SemanticError {
		String valor_inicial = token.getLexeme();
		String tipo = "";
		for (String id : lista_id) {
			for(String simbol : tabela_simbolos) {
				if(simbol.contains(id)) {
					throw new SemanticError("identificador já declarado");
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
			if(tipo == "int64") {
				codigo.add("ldc.i8 "+valor_inicial.replace("_", "")+"\n");
			}
			if(tipo == "float64") {
				codigo.add("ldc.r8 "+valor_inicial.replace("_","")+"\n");
			}
			if(tipo == "char") {
				if(valor_inicial == "\t") {
					codigo.add("ldstr \"" + valor_inicial + "\" \n");
				}else if (valor_inicial == "\n") {
					codigo.add("ldstr \"" + valor_inicial + "\" \n");
				}else {
					codigo.add("ldstr \" \" \n");
				}				
			}
			if(tipo == "string") {
				codigo.add("ldstr "+valor_inicial.replace("\'", "\"")+"\n");
			}
			if(tipo == "bool" && valor_inicial == "true") {
				codigo.add("ldc.i4.1\n");
			}
			if(tipo == "bool" && valor_inicial == "false") {
				codigo.add("ldc.i4.0\n");
			}
			
			codigo.add("stloc " + id + "\n");
		}
		
		lista_id.clear();
	}

	private void acao22(Token token) throws SemanticError {
		String id = token.getLexeme();
		boolean existe = false;
		for (String simbolos : tabela_simbolos) {
			if(simbolos.contains(id)) {
				existe = true;
				break;
			}
		}
		if(existe == false)
			throw new SemanticError("identificador não declarado");
		
		identificador = id;
	}

	private void acao23() {

	}

	private void acao24() throws SemanticError {
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
				throw new SemanticError("identificador não declarado");
			
			codigo.add("call string [mscorlib]System.Console::ReadLine()\n");
			if(tipo == "int64") {
				codigo.add("call int64 [mscorlib]System.Int64::Parse(string)\n");
			}else if(tipo == "float64") {
				codigo.add("call float64 [mscorlib]System.Double::Parse(string)\n");
			}else if(tipo == "bool") {
				codigo.add("call bool [mscorlib]System.Boolean::Parse(string)\n");
			}
			codigo.add("stloc"+id+"\n");
		}
	}

	private void acao25(Token token) {
		lista_id.add(token.getLexeme());
	}

	private void acao26() {

	}

	private void acao27() {

	}

	private void acao28() {

	}

	private void acao29() {

	}

	private void acao30() {

	}

	private void acao31() {

	}

	private void acao32() {

	}

	private void acao33(Token token) throws SemanticError {
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
			throw new SemanticError("identificador não declarado");
		
		pilha_tipos.add(tipo);
		if(tipo == "int64") {
			codigo.add("conv.r8\n");
		}
		codigo.add("ldloc"+id+"\n");
	}
}
