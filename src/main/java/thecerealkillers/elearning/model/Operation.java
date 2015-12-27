package thecerealkillers.elearning.model;


/**
 * Created by Dani.
 */
public class Operation {

    private String operationName;

    public Operation(String operationName) {
        this.operationName = operationName;
    }

    public Operation() {
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Operation operation = (Operation) o;

        return operationName != null ? operationName.equals(operation.operationName) : operation.operationName == null;

    }

    @Override
    public int hashCode() {
        return operationName != null ? operationName.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "operationName='" + operationName + '\'' +
                '}';
    }
}
