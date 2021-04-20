public class LexicalError extends AnalysisError
{
    public LexicalError(String msg, int position, String lex)
	 {
        super(msg, position, lex);
    }

    public LexicalError(String msg)
    {
        super(msg);
    }
}