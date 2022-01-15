module br.com.next.projetobanconext {
    requires javafx.controls;
    requires javafx.fxml;


    opens br.com.next.projetobanconext to javafx.fxml;
    exports br.com.next.projetobanconext;
    opens br.com.next.projetobanconext.model to javafx.fxml;
    exports br.com.next.projetobanconext.model;
    exports br.com.next.projetobanconext.controller;
    opens br.com.next.projetobanconext.controller to javafx.fxml;
}