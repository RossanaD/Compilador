public class Token
{
    private int id;
    private String lexeme;
    private int position;

    public Token(int id, String lexeme, int position)
    {
        this.id = id;                        //classe, codificada, observar o arquivo Constants.java
        this.lexeme = lexeme;
        this.position = position;			//POSICAO, onde inicia o token => "converter" position em linha
    }

    public final int getId()
    {
        return id;
    }

    public final String getLexeme()
    {
        return lexeme;
    }

    public final int getPosition()
    {
        return position;
    }

    public String toString()
    {
        return position + "     " + getClasseConst(id) + "     " + lexeme;
    };
    
    public String getClasseConst(int id) {
    	switch (id) {
		case 2:
			return "identificador";
		case 3:
			return "constante int";
		case 4:
			return "constante float";
		case 5:
			return "constante char";
		case 6:
			return "constante string";
		case 7:
			return "palavra reservada";
		case 8:
			return "palavra reservada";
		case 9:
			return "palavra reservada";
		case 10:
			return "palavra reservada";
		case 11:
			return "palavra reservada";
		case 12:
			return "palavra reservada";
		case 13:
			return "palavra reservada";
		case 14:
			return "palavra reservada";
		case 15:
			return "palavra reservada";
		case 16:
			return "palavra reservada";
		case 17:
			return "símbolo especial";
		case 18:
			return "símbolo especial";
		case 19:
			return "símbolo especial";
		case 20:
			return "símbolo especial";
		case 21:
			return "símbolo especial";
		case 22:
			return "símbolo especial";
		case 23:
			return "símbolo especial";
		case 24:
			return "símbolo especial";
		case 25:
			return "símbolo especial";
		case 26:
			return "símbolo especial";
		case 27:
			return "símbolo especial";
		case 28:
			return "símbolo especial";
		case 29:
			return "símbolo especial";
		case 30:
			return "símbolo especial";
		case 31:
			return "símbolo especial";
		case 32:
			return "símbolo especial";
		case 33:
			return "símbolo especial";
		case 34:
			return "símbolo especial";
		case 35:
			return "símbolo especial";
		case 36:
			return "símbolo especial";
		case 37:
			return "símbolo especial";
		case 38:
			return "símbolo especial";
		default:
			break;
		}
    	return null;
    }
}
