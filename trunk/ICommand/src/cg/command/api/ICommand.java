package cg.command.api;

// command = command-key + paramters
//
public interface ICommand
{
  public IResult execute();
  public IResult execute( ICommandContext context );
}
