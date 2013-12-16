package inhibeans.property;

/**
 * Inhibitory version of {@link javafx.beans.property.SimpleFloatProperty}.
 */
public class SimpleFloatProperty
extends javafx.beans.property.SimpleFloatProperty
implements InhibitoryProperty<Number> {

    private boolean blocked = false;
    private boolean fireOnRelease = false;

    @Override
    public AutoCloseable block() {
        blocked = true;
        return this;
    }

    @Override
    public void release() {
        blocked = false;
        if(fireOnRelease) {
            fireOnRelease = false;
            super.fireValueChangedEvent();
        }
    }

    @Override
    protected void fireValueChangedEvent() {
        if(blocked)
            fireOnRelease = true;
        else
            super.fireValueChangedEvent();
    }


    /********************************
     *** Superclass constructors. ***
     ********************************/

    public SimpleFloatProperty() {
        super();
    }

    public SimpleFloatProperty(float initialValue) {
        super(initialValue);
    }

    public SimpleFloatProperty(Object bean, String name) {
        super(bean, name);
    }

    public SimpleFloatProperty(Object bean, String name, float initialValue) {
        super(bean, name, initialValue);
    }
}
