public class SemanticError extends AnalysisError
{
    public SemanticError(String msg, int position, String lex)
	 {
        super(msg, position, lex);
    }

    public SemanticError(String msg)
    {
        super(msg);
    }
}