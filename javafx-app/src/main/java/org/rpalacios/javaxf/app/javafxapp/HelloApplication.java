package org.rpalacios.javaxf.app.javafxapp;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.rpalacios.javaxf.app.javafxapp.models.Products;
import org.rpalacios.javaxf.app.javafxapp.services.ProductService;
import org.rpalacios.javaxf.app.javafxapp.services.ProductServiceWPC;

import java.io.IOException;

import static javafx.collections.FXCollections.observableArrayList;

public class HelloApplication extends Application {

    private final ProductService service = new ProductServiceWPC();


    private final ObservableList<Products> products = observableArrayList(
            new Products("Laptop", "High performance laptop", 1200L),
            new Products("Smartphone", "Latest model smartphone", 800L),
            new Products("Headphones", "Noise-cancelling headphones", 200L)
    );

    private Products productSelected = null;

    private final TextField nameField =  new TextField();
    private final TextField descriptionField = new TextField();;
    private final TextField priceField = new TextField();

    private final Button addButton = new Button("Add");


    @Override
    public void start(Stage stage) throws IOException {
        TableView<Products> tableView =  new TableView<>();
        TableColumn<Products,String> nameColumn = new TableColumn<>("name");
        TableColumn<Products,String> descriptionColumn = new TableColumn<>("description");
        TableColumn<Products,String> priceColumn = new TableColumn<>("price");

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Products, Void> deleteColumn = new TableColumn<>("Delete");
        deleteColumn.setCellFactory(param -> new TableCell<>(){
            private final Button deleteButton = new Button("Delete");

            {
                deleteButton.setOnAction(event -> {
                    Products product = getTableView().getItems().get(getIndex());
                    service.delete(product);
                    tableView.getItems().remove(product);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if(empty){
                    setGraphic(null);
                }else{
                    setGraphic(deleteButton);
                }
            }
        });
        TableColumn<Products, Void> editColumn = new TableColumn<>("Update");
        editColumn.setCellFactory(param -> new TableCell<>(){
            private final Button editButton = new Button("Update");
            {
                editButton.setOnAction(event ->{
                    productSelected = getTableView().getItems().get(getIndex());
                    nameField.setText(productSelected.getName());
                    descriptionField.setText(productSelected.getDescription());
                    priceField.setText(productSelected.getPrice().toString());
                    addButton.setText("Update");
                });
            }

            @Override
            protected void updateItem(Void unused, boolean b) {
                super.updateItem(unused, b);
                if(b){
                    setGraphic(null);
                } else {
                    setGraphic(editButton);
                }
            }
        });

        tableView.getColumns().addAll(nameColumn,descriptionColumn,priceColumn,deleteColumn,editColumn);
        tableView.setItems(FXCollections.observableArrayList(service.findAll()));

        nameField.setPromptText("Nombre");
        descriptionField.setPromptText("Description");
        priceField.setPromptText("Price");

        addButton.setOnAction(event -> {
            String name = nameField.getText();
            String description = descriptionField.getText();
            String priceText = priceField.getText();
            if(!name.isBlank() && !description.isBlank() && !priceText.isBlank()){
                try{
                    Long price = Long.parseLong(priceText);

                    if(productSelected ==null){
                        Products product = new Products(name, description, price);
                        Products createdProduct = service.save(product);
                        tableView.getItems().add(createdProduct);

                    }else{
                        productSelected.setName(name);
                        productSelected.setDescription(description);
                        productSelected.setPrice(price);
                        service.update(productSelected);
                        tableView.refresh();
                        productSelected = null;
                        addButton.setText("Add");
                    }

                    nameField.clear();
                    descriptionField.clear();
                    priceField.clear();

                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Price must be a valid number");
                    alert.showAndWait();
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR, "All fields are required");
                alert.showAndWait();
            }
        });

        Button clearButton = new Button("Clear");
        clearButton.setOnAction(event ->{
            productSelected = null;
            addButton.setText("Add");
            nameField.clear();
            descriptionField.clear();
            priceField.clear();
        });


        HBox form = new HBox(10, nameField, descriptionField, priceField, addButton,clearButton);
        form.setPadding(new Insets(10));
        VBox vbox = new VBox(form,tableView);
        Scene scene = new Scene(vbox, 620, 400);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}