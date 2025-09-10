package beans;


import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.bean.ManagedBean;
import jakarta.inject.Named;
import lombok.Data;

import java.io.Serializable;

@Data
@Named("y")
@SessionScoped
public class YCoordBean implements Serializable {
    private float y;
}
