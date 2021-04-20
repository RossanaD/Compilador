public class SyntaticError extends AnalysisError
{
    public SyntaticError(String msg, int position, String lex)
	 {
        super(msg, position, lex);
    }

    public SyntaticError(String msg)
    {
        super(msg);
    }
}