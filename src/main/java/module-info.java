module br.com.next.projetobanconext {
    requires javafx.controls;
    requires javafx.fxml;


    opens br.com.next.projetobanconext to javafx.fxml;
    exports br.com.next.projetobanconext;
    exports br.com.next.projetobanconext.view;
    opens br.com.next.projetobanconext.view to javafx.fxml;
    exports br.com.next.projetobanconext.model;
    opens br.com.next.projetobanconext.model to javafx.fxml;
    exports br.com.next.projetobanconext.controller;
    opens br.com.next.projetobanconext.controller to javafx.fxml;
}