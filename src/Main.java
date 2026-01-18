import controller.GameController;
import model.GameModel;
import view.GameView;

void main() {
    GameModel model = new GameModel();
    GameView view = new GameView();
    GameController controller = new GameController(model, view);

    view.setController(controller);
}
