public class AnalysisError extends Exception
{
    private int position;
    private String lexema;

    public AnalysisError(String msg, int position, String lexema)
    {
        super(msg);
        this.position = position;
        setLexema(lexema);
    }

    public AnalysisError(String msg)
    {
        super(msg);
        this.position = -1;
    }

    public int getPosition()
    {
        return position;
    }

    public String toString()
    {
        return super.toString() + ", @ "+position;
    }

	public String getLexema() {
		return lexema;
	}

	public void setLexema(String lexema) {
		if(lexema.length() != 1) {
			this.lexema = "";
		}else {
			this.lexema = lexema;
		}		
	}
    
}
