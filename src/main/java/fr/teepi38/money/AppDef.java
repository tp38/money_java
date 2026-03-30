package fr.teepi38.money;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AppDef {
    @Builder.Default private String name = "AnFunnyApp";
    @Builder.Default private String version = "x.y.z";
    @Builder.Default private Date date = new Date();
    @Builder.Default private String author = "Thierry Probst";

}
