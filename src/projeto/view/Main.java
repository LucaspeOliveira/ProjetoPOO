package projeto.view;

import java.sql.SQLException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import projeto.controller.ControllEletronico;
import projeto.controller.ControllMoveis;
import projeto.controller.ControllRoupa;
import projeto.controller.ControllUtensilios;
import projeto.model.EntityUtensilios;
import javafx.geometry.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Main extends Application {
    ControllRoupa cRoupa = new ControllRoupa();
    ControllEletronico cEletronico = new ControllEletronico();
    ControllMoveis cMoveis = new ControllMoveis();
    ControllUtensilios cUtensilios = new ControllUtensilios();
    private Stage primaryStage;

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        // Inicia na tela de login
        primaryStage.setTitle("Sistema de Estoque");
        primaryStage.setScene(TelaInicial());
        primaryStage.show();
    }

    private Scene TelaInicial() {
        // Criar os botões
        Button btnRoupa = criarBotao("Sistema de Roupa", "#FFF3AD");
        Button btnEletronicos = criarBotao("Sistema de Eletro", "#14A44D");
        Button btnUtensilios = criarBotao("Sistema de Utensilios", "#FF6F00");
        Button btnMoveis = criarBotao("Sistema Moveis", "#00A7F7");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(20);
        grid.setHgap(40);
        grid.add(btnRoupa, 0, 0);
        grid.add(btnUtensilios, 1, 0);
        grid.add(btnEletronicos, 0, 1);
        grid.add(btnMoveis, 1, 1);
        StackPane painelCentral = new StackPane(grid);
        painelCentral.setStyle(
                "-fx-background-color: #D3D3D3; -fx-border-color: black; -fx-border-radius: 20; -fx-background-radius: 20; -fx-padding: 40;");
        StackPane root = new StackPane(painelCentral);
        root.setStyle("-fx-background-color: #808080;");
        Scene scene = new Scene(root, 800, 500);
        primaryStage.setTitle("Tela Inicial do Sistema");
        primaryStage.setScene(scene);
        primaryStage.show();
        // Ações dos botões:
        btnRoupa.setOnAction(e -> {
            primaryStage.setScene(telaMenu(cRoupa, cEletronico, cMoveis, cUtensilios, 1));
        });
        btnUtensilios.setOnAction(e -> {
            primaryStage.setScene(telaMenu(cRoupa, cEletronico, cMoveis, cUtensilios, 4));
        });
        btnEletronicos.setOnAction(e -> {
            primaryStage.setScene(telaMenu(cRoupa, cEletronico, cMoveis, cUtensilios, 2));
        });
        btnMoveis.setOnAction(e -> {
            primaryStage.setScene(telaMenu(cRoupa, cEletronico, cMoveis, cUtensilios, 3));
        });
        return scene;
    }

    private Scene telaMenu(ControllRoupa cRoupa, ControllEletronico cEletronico, ControllMoveis cMoveis, ControllUtensilios cUtensilios, int indc) {
        Button btnAdicionar = criarBotao("Adicionar", "WHITE");
        Button btnRemover = criarBotao("Remover", "WHITE");
        Button btnModificar = criarBotao("Modificar", "WHITE");
        Button btnMostrar = criarBotao("Mostrar", "WHITE");
        Button btnVoltar = criarBotao("Voltar", "WHITE");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(20);
        grid.setHgap(40);
        grid.add(btnAdicionar, 0, 0);
        grid.add(btnRemover, 1, 0);
        grid.add(btnModificar, 0, 1);
        grid.add(btnMostrar, 1, 1);
        HBox boxVoltar = new HBox(btnVoltar);
        boxVoltar.setAlignment(Pos.CENTER_LEFT);
        boxVoltar.setPadding(new Insets(10, 0, 0, 20));
        VBox layoutVertical = new VBox();
        layoutVertical.setSpacing(20);
        layoutVertical.getChildren().addAll(grid, boxVoltar);
        layoutVertical.setAlignment(Pos.CENTER);
        StackPane painel = new StackPane(layoutVertical);
        painel.setStyle("-fx-background-color: #D3D3D3; " +
                "-fx-border-color: black; " +
                "-fx-border-radius: 20; " +
                "-fx-background-radius: 20; " +
                "-fx-padding: 40;");
        // Ações dos Botões
        btnVoltar.setOnAction(e -> {
            primaryStage.setScene(TelaInicial());
        });
        btnAdicionar.setOnAction(e -> {
            if (indc == 4) {
                primaryStage.setScene(TelaAdicionarU(cRoupa, cEletronico, cMoveis, cUtensilios, indc));
            } else if (indc == 3){
                primaryStage.setScene(TelaAdicionarM(cRoupa, cEletronico, cMoveis, cUtensilios, indc));
            } else if (indc == 2){

            } else {

            }
        });
        return new Scene(new StackPane(painel), 800, 500, Color.LIGHTGRAY);

    }

    public Scene TelaAdicionarU(ControllRoupa cRoupa, ControllEletronico cEletronico, ControllMoveis cMoveis,
            ControllUtensilios cUtensilios, int indc) {
        // Campos de texto
        TextField txtNome = criarCampo("Adicionar nome");
        TextField txtTipo = criarCampo("Adicionar Tipo");
        TextField txtQuantidade = criarCampo("Adicionar Quantidade");

        VBox campos = new VBox(15, txtNome, txtTipo, txtQuantidade);
        campos.setAlignment(Pos.CENTER_LEFT);
        campos.setPadding(new Insets(0, 0, 0, 50));

        Button btnVoltar = criarBotao("voltar", "WHITE");
        Button btnSalvar = criarBotao("Salvar", "WHITE");

        BorderPane botoesRodape = new BorderPane();
        botoesRodape.setLeft(btnVoltar);
        botoesRodape.setRight(btnSalvar);
        botoesRodape.setPadding(new Insets(30, 50, 20, 50));

        VBox painelInterno = new VBox(30, campos, botoesRodape);
        painelInterno.setAlignment(Pos.CENTER_LEFT);
        painelInterno.setStyle("-fx-background-color: #D3D3D3; " +
                "-fx-border-color: black; " +
                "-fx-border-radius: 20; " +
                "-fx-background-radius: 20; " +
                "-fx-padding: 40;");

        StackPane painelCentral = new StackPane(painelInterno);
        painelCentral.setStyle("-fx-background-color: #808080;");
        painelCentral.setPrefSize(800, 500);
        // fUNÇÕES:
        btnVoltar.setOnAction(e -> {
            primaryStage.setScene(telaMenu(cRoupa, cEletronico, cMoveis, cUtensilios, indc));
        });
        btnSalvar.setOnAction(e -> {
            EntityUtensilios ut = new EntityUtensilios();
            ut.setNome(txtNome.getText());
            ut.setQuantidade(Integer.parseInt(txtQuantidade.getText()));
            ut.setTipo(txtTipo.getText());
            try {
                cUtensilios.addUtensilios(ut);
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        return new Scene(painelCentral);
    }

    public Scene TelaAdicionarM(ControllRoupa cRoupa, ControllEletronico cEletronico, ControllMoveis cMoveis, ControllUtensilios cUtensilios, int indc) {
        // Campos de entrada
        TextField txtNome = criarCampo("Adicionar nome");
        TextField txtTipo = criarCampo("Adicionar Tipo");
        TextField txtQuantidade = criarCampo("Adicionar Quantidade");
        TextField txtMarca = criarCampo("Adicionar Marca");

        VBox campos = new VBox(15, txtNome, txtTipo, txtQuantidade, txtMarca);
        campos.setAlignment(Pos.CENTER_LEFT);
        campos.setPadding(new Insets(0, 0, 0, 50));

        // Botões
        Button btnVoltar = criarBotao("voltar", "WHITE");
        Button btnSalvar = criarBotao("Salvar", "WHITE");

        BorderPane botoesRodape = new BorderPane();
        botoesRodape.setLeft(btnVoltar);
        botoesRodape.setRight(btnSalvar);
        botoesRodape.setPadding(new Insets(30, 50, 20, 50));

        // Painel central com fundo e borda
        VBox painelInterno = new VBox(30, campos, botoesRodape);
        painelInterno.setAlignment(Pos.CENTER_LEFT);
        painelInterno.setStyle("-fx-background-color: #D3D3D3; " +
                "-fx-border-color: black; " +
                "-fx-border-radius: 20; " +
                "-fx-background-radius: 20; " +
                "-fx-padding: 40;");

        StackPane painelFundo = new StackPane(painelInterno);
        painelFundo.setStyle("-fx-background-color: #808080;");
        painelFundo.setPrefSize(800, 500);
        // Funções:
        btnVoltar.setOnAction(e -> {
            primaryStage.setScene(telaMenu(cRoupa, cEletronico, cMoveis, cUtensilios, indc));
        });
        return new Scene(painelFundo);
    }

    private TextField criarCampo(String prompt) {
        TextField txt = new TextField();
        txt.setPromptText(prompt);
        txt.setPrefWidth(300);
        txt.setPrefHeight(35);
        txt.setFont(Font.font("Arial", 14));
        txt.setStyle("-fx-border-color: black; -fx-border-width: 2;");
        return txt;
    }

    private Button criarBotao(String texto, String corHex) {
        Button btn = new Button(texto);
        btn.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        btn.setPrefSize(160, 50);
        btn.setStyle(
                "-fx-background-color: " + corHex + ";" +
                        "-fx-text-fill: black;" +
                        "-fx-background-radius: 15;" +
                        "-fx-border-radius: 15;" +
                        "-fx-border-color: black;" +
                        "-fx-border-width: 2;");
        return btn;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
