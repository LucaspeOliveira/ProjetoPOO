package projeto.view;

import java.sql.SQLException;
import java.util.List;

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
import projeto.model.EntityEletronico;
import projeto.model.EntityMoveis;
import projeto.model.EntityRoupa;
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

    private Scene telaMenu(ControllRoupa cRoupa, ControllEletronico cEletronico, ControllMoveis cMoveis,
            ControllUtensilios cUtensilios, int indc) {
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
            if (indc == 1) {
                primaryStage.setScene(TelaAdicionarR(cRoupa, cEletronico, cMoveis, cUtensilios, indc, 1));
            } else if (indc == 2) {
                primaryStage.setScene(TelaAdicionarE(cRoupa, cEletronico, cMoveis, cUtensilios, indc, 1));
            } else if (indc == 3) {
                primaryStage.setScene(TelaAdicionarM(cRoupa, cEletronico, cMoveis, cUtensilios, indc, 1));
            } else if (indc == 4) {
                primaryStage.setScene(TelaAdicionarU(cRoupa, cEletronico, cMoveis, cUtensilios, indc, 1));
            }
        });
        btnModificar.setOnAction(e -> {
            if (indc == 1) {
                primaryStage.setScene(TelaAdicionarR(cRoupa, cEletronico, cMoveis, cUtensilios, indc, 2));
            } else if (indc == 2) {
                primaryStage.setScene(TelaAdicionarE(cRoupa, cEletronico, cMoveis, cUtensilios, indc, 2));
            } else if (indc == 3) {
                primaryStage.setScene(TelaAdicionarM(cRoupa, cEletronico, cMoveis, cUtensilios, indc, 2));
            } else if (indc == 4) {
                primaryStage.setScene(TelaAdicionarU(cRoupa, cEletronico, cMoveis, cUtensilios, indc, 2));
            }
        });
        btnMostrar.setOnAction(e -> {
            primaryStage.setScene(ListagemEstoque(cRoupa, cEletronico, cMoveis, cUtensilios, indc));
        });
        btnRemover.setOnAction(e -> {
            primaryStage.setScene(TelaRemoverItem(cRoupa, cEletronico, cMoveis, cUtensilios, indc));
        });
        return new Scene(new StackPane(painel), 800, 500, Color.LIGHTGRAY);

    }

    public Scene TelaAdicionarU(ControllRoupa cRoupa, ControllEletronico cEletronico, ControllMoveis cMoveis,
            ControllUtensilios cUtensilios, int indc, int tipo) {
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
            if (tipo == 1) {
                try {
                    cUtensilios.addUtensilios(ut);
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            } else {
                try {
                    cUtensilios.alterar(ut);
                    ;
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        return new Scene(painelCentral);
    }

    public Scene TelaAdicionarM(ControllRoupa cRoupa, ControllEletronico cEletronico, ControllMoveis cMoveis,
            ControllUtensilios cUtensilios, int indc, int tipo) {
        TextField txtNome = criarCampo("Adicionar nome");
        TextField txtTipo = criarCampo("Adicionar Tipo");
        TextField txtQuantidade = criarCampo("Adicionar Quantidade");
        TextField txtMarca = criarCampo("Adicionar Marca");
        TextField txtTamanho = criarCampo("Adicionar Tamanho");

        VBox campos = new VBox(15, txtNome, txtTipo, txtQuantidade, txtMarca, txtTamanho);
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

        StackPane painelFundo = new StackPane(painelInterno);
        painelFundo.setStyle("-fx-background-color: #808080;");
        painelFundo.setPrefSize(800, 500);
        // Funções:
        btnSalvar.setOnAction(e -> {
            EntityMoveis mo = new EntityMoveis();
            mo.setMarca(txtMarca.getText());
            mo.setNome(txtNome.getText());
            mo.setQuantidade(Integer.parseInt(txtQuantidade.getText()));
            mo.setTamanho(txtTamanho.getText());
            mo.setTipo(txtTipo.getText());
            if (tipo == 1) {
                try {
                    cMoveis.addMoveis(mo);
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            } else {
                try {
                    cMoveis.alterar(mo);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnVoltar.setOnAction(e -> {
            primaryStage.setScene(telaMenu(cRoupa, cEletronico, cMoveis, cUtensilios, indc));
        });
        return new Scene(painelFundo);
    }

    public Scene TelaAdicionarE(ControllRoupa cRoupa, ControllEletronico cEletronico, ControllMoveis cMoveis,
            ControllUtensilios cUtensilios, int indc, int tipo) {
        // Campos de entrada
        TextField txtNome = criarCampo("Adicionar Nome");
        TextField txtTipo = criarCampo("Adicionar tipo");
        TextField txtMarca = criarCampo("Adicionar marca");
        TextField txtQuantidade = criarCampo("Adicionar quantidade");

        VBox campos = new VBox(15, txtNome, txtTipo, txtMarca, txtQuantidade);
        campos.setAlignment(Pos.CENTER_LEFT);
        campos.setPadding(new Insets(0, 0, 0, 50));

        // Botões
        Button btnVoltar = criarBotao("voltar", "WHITE");
        Button btnSalvar = criarBotao("salvar", "WHITE");

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
        StackPane fundo = new StackPane(painelInterno);
        fundo.setStyle("-fx-background-color: #808080;");
        fundo.setPrefSize(800, 500);

        btnSalvar.setOnAction(e -> {
            EntityEletronico ele = new EntityEletronico();
            ele.setMarca(txtMarca.getText());
            ele.setNome(txtNome.getText());
            ele.setQuantidade(Integer.parseInt(txtQuantidade.getText()));
            ele.setTipo(txtTipo.getText());
            if (tipo == 1) {
                try {
                    cEletronico.addEletronico(ele);
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            } else {
                try {
                    cEletronico.alterar(ele);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnVoltar.setOnAction(e -> {
            primaryStage.setScene(telaMenu(cRoupa, cEletronico, cMoveis, cUtensilios, indc));
        });
        return new Scene(fundo);
    }

    public Scene TelaAdicionarR(ControllRoupa cRoupa, ControllEletronico cEletronico, ControllMoveis cMoveis,
            ControllUtensilios cUtensilios, int indc, int tipo) {
        TextField txtNome = criarCampo("Adicionar Nome");
        TextField txtTipo = criarCampo("Adicionar tipo");
        TextField txtMarca = criarCampo("Adicionar marca");
        TextField txtQuantidade = criarCampo("Adicionar quantidade");
        VBox colunaEsquerda = new VBox(15, txtNome, txtTipo, txtMarca, txtQuantidade);
        colunaEsquerda.setAlignment(Pos.TOP_LEFT);
        TextField txtGenero = criarCampo("Adicionar Genero");
        TextField txtTamanho = criarCampo("Adicionar Tamanho");
        VBox colunaDireita = new VBox(15, txtGenero, txtTamanho);
        colunaDireita.setAlignment(Pos.TOP_LEFT);
        HBox campos = new HBox(60, colunaEsquerda, colunaDireita);
        campos.setAlignment(Pos.CENTER_LEFT);
        campos.setPadding(new Insets(30, 50, 0, 50));
        // Botões
        Button btnVoltar = criarBotao("voltar", "WHITE");
        Button btnSalvar = criarBotao("salvar", "WHITE");
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

        StackPane root = new StackPane(painelInterno);
        root.setStyle("-fx-background-color: #808080;");
        root.setPrefSize(1000, 550);

        btnSalvar.setOnAction(e -> {
            EntityRoupa ro = new EntityRoupa();
            ro.setMarca(txtMarca.getText());
            ro.setNome(txtNome.getText());
            ro.setQuantidade(Integer.parseInt(txtQuantidade.getText()));
            ro.setTamanho(txtTamanho.getText());
            ro.setTipo(txtTipo.getText());
            ro.setGenero(txtGenero.getText());
            if (tipo == 1) {
                try {
                    cRoupa.addRoupa(ro);
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            } else {
                try {
                    cRoupa.alterar(ro);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnVoltar.setOnAction(e -> {
            primaryStage.setScene(telaMenu(cRoupa, cEletronico, cMoveis, cUtensilios, indc));
        });
        return new Scene(root);
    }

    public Scene TelaRemoverItem(ControllRoupa cRoupa, ControllEletronico cEletronico, ControllMoveis cMoveis,
            ControllUtensilios cUtensilios, int indc) {
        TextField txtId = new TextField();
        txtId.setPromptText("Adicionar ID");
        txtId.setPrefWidth(300);
        txtId.setPrefHeight(35);
        txtId.setFont(Font.font("Arial", 14));
        txtId.setStyle("-fx-border-color: black; -fx-border-width: 2;");
        Button btnVoltar = criarBotao("voltar", "WHITE");
        Button btnRemover = criarBotao("remover", "WHITE");
        BorderPane botoesRodape = new BorderPane();
        botoesRodape.setLeft(btnVoltar);
        botoesRodape.setRight(btnRemover);
        botoesRodape.setPadding(new Insets(30, 50, 20, 50));

        VBox conteudo = new VBox(40, txtId, botoesRodape);
        conteudo.setAlignment(Pos.CENTER);
        conteudo.setPadding(new Insets(30));

        // Painel com borda e fundo claro
        VBox painelInterno = new VBox(conteudo);
        painelInterno.setAlignment(Pos.CENTER);
        painelInterno.setStyle("-fx-background-color: #D3D3D3;" +
                "-fx-border-color: black;" +
                "-fx-border-radius: 20;" +
                "-fx-background-radius: 20;" +
                "-fx-padding: 40;");

        StackPane fundo = new StackPane(painelInterno);
        fundo.setStyle("-fx-background-color: #808080;");
        fundo.setPrefSize(800, 500);
        btnRemover.setOnAction(e -> {
            int id = Integer.parseInt(txtId.getText());
            if (indc == 1) {
                cRoupa.excluiRoupa(id);
            } else if (indc == 2) {
                cEletronico.excluiEletronico(id);
            } else if (indc == 3) {
                cMoveis.excluiMoveis(id);
            } else if (indc == 4) {
                cUtensilios.excluiUtensilios(id);
            }
        });
        btnVoltar.setOnAction(e -> {
            primaryStage.setScene(telaMenu(cRoupa, cEletronico, cMoveis, cUtensilios, indc));
        });

        return new Scene(fundo);
    }

    private Scene ListagemEstoque(ControllRoupa cRoupa, ControllEletronico cEletronico, ControllMoveis cMoveis,
            ControllUtensilios cUtensilios, int indc) {
        FlowPane grid = new FlowPane();
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(10));
        grid.setPrefWrapLength(750);
        if (indc == 1) {
            List<EntityRoupa> lista = cRoupa.listar(); // busca roupas
            for (EntityRoupa item : lista) {
                VBox card = new VBox(5);
                card.setPadding(new Insets(10));
                card.setAlignment(Pos.CENTER);
                card.setStyle("-fx-border-color: black; -fx-background-color: #d3d3d3;");
                card.setPrefSize(100, 120);
                Label nomeRoupa = new Label(item.getNome());

                card.getChildren().addAll(nomeRoupa);
                grid.getChildren().add(card);
            }
        } else if (indc == 2) {
            List<EntityEletronico> lista = cEletronico.listar();
            for (EntityEletronico item : lista) {
                VBox card = new VBox(5);
                card.setPadding(new Insets(10));
                card.setAlignment(Pos.CENTER);
                card.setStyle("-fx-border-color: black; -fx-background-color: #d3d3d3;");
                card.setPrefSize(100, 120);
                Label nomeRoupa = new Label(item.getNome());

                card.getChildren().addAll(nomeRoupa);
                grid.getChildren().add(card);
            }
        } else if (indc == 3) {
            List<EntityMoveis> lista = cMoveis.listar();
            for (EntityMoveis item : lista) {
                VBox card = new VBox(5);
                card.setPadding(new Insets(10));
                card.setAlignment(Pos.CENTER);
                card.setStyle("-fx-border-color: black; -fx-background-color: #d3d3d3;");
                card.setPrefSize(100, 120);
                Label nomeRoupa = new Label(item.getNome());

                card.getChildren().addAll(nomeRoupa);
                grid.getChildren().add(card);
            }
        } else if (indc == 4) {
            List<EntityUtensilios> lista = cUtensilios.listar();
            for (EntityUtensilios item : lista) {
                VBox card = new VBox(5);
                card.setPadding(new Insets(10));
                card.setAlignment(Pos.CENTER);
                card.setStyle("-fx-border-color: black; -fx-background-color: #d3d3d3;");
                card.setPrefSize(100, 120);
                Label nomeRoupa = new Label(item.getNome());

                card.getChildren().addAll(nomeRoupa);
                grid.getChildren().add(card);
            }
        }

        ScrollPane scrollPane = new ScrollPane(grid);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefViewportHeight(380);

        Button btnVoltar = new Button("Voltar");
        btnVoltar.setMinWidth(100);
        btnVoltar.setFont(new Font(14));
        btnVoltar.setTextFill(Color.BLACK);
        btnVoltar.setOnAction(e -> {
            primaryStage.setScene(telaMenu(cRoupa, cEletronico, cMoveis, cUtensilios, indc));
        });

        HBox boxVoltar = new HBox(btnVoltar);
        boxVoltar.setAlignment(Pos.CENTER);
        boxVoltar.setPadding(new Insets(10));

        VBox layout = new VBox(10, scrollPane, boxVoltar);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #808080; -fx-border-color: black; -fx-border-width: 2px;");

        return new Scene(layout, 800, 500, Color.LIGHTGRAY);
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
