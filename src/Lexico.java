public class Lexico implements Constants
{
    private int position;
    private String input;
    private int linha = 1;
    private int auxEstado;
    private int count;
    private String simbolo;
    private String[] array;
    private int idx = 0;

    public int getLinha() {
    	return linha;
    }
    
    public Lexico()
    {
        this("");
    }


    public Lexico(String input)
    {
        setInput(input);
    }


    public void setInput(String input)
    {
        setArray(input);
        this.input = input;
        setPosition(0);
    }


    public void setPosition(int pos)
    {
        position = pos;
    }


    public void setArray(String inp) {
        array = inp.split("\\r\\n|\\n|\\r");
    }
    
    public Token nextToken() throws LexicalError
    {
        if ( ! hasInput() )
            return null;


        int start = position;


        int state = 0;
        int lastState = 0;
        int endState = -1;
        int end = -1;


        while (hasInput())
        {
            lastState = state;
            state = nextState(nextChar(), state);
            auxEstado = lastState;
            isComentarioBloco(lastState, state);
            isComentarioLinha(lastState, state);

 


            if (state < 0)                
                break;
            
            else
            {
                if (tokenForState(state) >= 0)
                {
                    endState = state;
                    end = position;
                }
            }
        }
        
        if (endState < 0 || (endState != state && tokenForState(lastState) == -2)) {   
            //verifica se ? comentario bloco
            if(auxEstado == 38) {
                linha = linha-count;
            }
            if(state == -1 || state == 4) {
                simbolo = input.substring(start, start+1);
            }
            //getLinha();
            throw new LexicalError(SCANNER_ERROR[lastState], linha);
        }
            
        position = end;

        int token = tokenForState(endState);
        if (token == 0) {
            return nextToken();
        }                  
        else
        {           
            String lexeme = input.substring(start, end);
            String aux = array[idx];
            linha = idx + 1;
            String[] arrayAux = aux.split("\\s");
            String comp = arrayAux[arrayAux.length-1];
            if (comp.contentEquals("")) {
            	idx++;
            }
            if(comp.contains(lexeme)) {
                 token = lookupToken(token, lexeme);
                 Token tokenAux = new Token(token, lexeme, linha);
                 idx++;
                 return tokenAux;
            }
            token = lookupToken(token, lexeme);
            return new Token(token, lexeme, linha);
        }
    }

    private int nextState(char c, int state)
    {
        int start = SCANNER_TABLE_INDEXES[state];
        int end   = SCANNER_TABLE_INDEXES[state+1]-1;

        while (start <= end)
        {
            int half = (start+end)/2;

           if (SCANNER_TABLE[half][0] == c)
                return SCANNER_TABLE[half][1];
            else if (SCANNER_TABLE[half][0] < c)
                start = half+1;
            else  //(SCANNER_TABLE[half][0] > c)
                end = half-1;
        }

        return -1;
    }

 
    private int tokenForState(int state)
    {
        if (state < 0 || state >= TOKEN_STATE.length)
            return -1;

        return TOKEN_STATE[state];
    }


    public int lookupToken(int base, String key)
    {
        int start = SPECIAL_CASES_INDEXES[base];
        int end   = SPECIAL_CASES_INDEXES[base+1]-1;


        while (start <= end)
        {
            int half = (start+end)/2;
            int comp = SPECIAL_CASES_KEYS[half].compareTo(key);

            
            if (comp == 0)
                return SPECIAL_CASES_VALUES[half];
            else if (comp < 0)
                start = half+1;
            else  //(comp > 0)
                end = half-1;
        }

        return base;
    }

    private boolean hasInput()
    {
        return position < input.length();
    }

    private char nextChar()
    {
        if (hasInput())  
            return input.charAt(position++);
            
                          
        else
            return (char) -1;
    }
    
//    public int getLinha() {
//        
//        if(position > 0 && c == '\n' ) {
//            return linha++;
//        }
//        c = nextChar();
//        return linha;
//    }

    public void isComentarioBloco(int estadoultimo, int estadoinci) {
        if(estadoinci == 21|| estadoultimo == 47 || ((estadoinci == 38) && (estadoinci != estadoultimo))) {
            count++;
            linha++;
        }
    }
    
    public void isComentarioLinha(int estadoFinal, int estadoInicial) {
        if(estadoFinal == 3 && estadoInicial == -1) {
            linha++;
        }
    }
    
    public String getSimbolo() {
        return simbolo;
    }

}
