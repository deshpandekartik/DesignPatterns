package maxKVisitors.util;

public interface Visitor
{
    public void visit(MyArray array);

    public void visit(MyVector vector);

    public void accept(MyArray array);

    public void accept(MyVector vector);
}