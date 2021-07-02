package Controllers;

import Utils.Controller;
import Utils.ViewLoader;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;


public class OutMainController extends Controller {
    @FXML
    private RadioMenuItem chuji;
    @FXML
    private RadioMenuItem gaoji;
    @FXML
    private RadioButton imgPlay;
    @FXML
    private RadioButton digitPlay;
    @FXML
    private Button startButton;
    @FXML
    private ToggleButton musicPlay;
    @FXML
    private ImageView imageOrigin;

    @FXML
    private AnchorPane mainPane;

    ImageView[] imageViews;
    int m;   //m是不在随机数组的那个数字


    @FXML
    void startButtonClick(ActionEvent event){
        imageOrigin.setImage(new Image("https://images.weserv.nl/?url=https://i0.hdslb.com/bfs/article/d9eb87d15697149e1aeef9f543ac1636f9db1a99.jpg", 300, 300, false, false));
        if(digitPlay.isSelected()){
            imageOrigin.setImage(null);
            if(chuji.isSelected()){


            }
            else{}
        }
        else {
            Image image = imageOrigin.getImage();
            if(image == null){
                Alert alert = new Alert(AlertType.WARNING, "请选择一张图片");
                alert.show();
                return;
            }

            mainPane.getChildren().clear();

            GridPane gridPane = new GridPane();
            int num = chuji.isSelected() ? 3 : 4; //宫格数 9/16

            int[] n = random(num*num);    //自定义的函数，产生逆序数为偶数的不重复数组
            imageViews = new ImageView[num*num];
            for(int i = 0, k = 0; i <= num-1; ++i) {
                for(int j = 0; j <= num-1; ++j, ++k) {
                    imageViews[k] = new ImageView(image);  //初始化数组
                    imageViews[k].setOnMouseClicked(new myevent(num));  //设置点击事件
                    imageViews[k].setViewport(new Rectangle2D((num == 3 ? 100 : 75) * j, (num == 3 ? 100 : 75) * i, num == 3 ? 100 : 75, num == 3 ? 100 : 75));    //切割图片
                }
            }
            for(int i=0; i<num*num-1; i++) { //按照产生的随机数将imageView数组加入面板
                gridPane.add(imageViews[n[i]], i%num, i/num);
            }
            m = findnum(n);             //找出那个不在随机数组里面的数字
            Image image2 = generateImage(0, 177.0 / 255, 65.0 / 255, 1);  //一个透明图，放在空格子中
            imageViews[m].setImage(image2);
            gridPane.add(imageViews[m], num == 3 ? 2 : 3, num == 3 ? 2 : 3);
            gridPane.setGridLinesVisible(true);
            mainPane.getChildren().add(gridPane);
        }

    }

    @FXML
    void inMenuItemClick(ActionEvent event) throws IOException {

        Stage loginStage = new Stage();
        ViewLoader.showStage(model, "/fxmls/logreg.fxml", "登陆注册", loginStage);
    }

    @FXML
    void imageChoiceMenuClick(ActionEvent event) throws IOException{
        Stage imageChoiceStage = new Stage();
        ViewLoader.showStage(model, "/fxmls/imageChoice.fxml", "选择图像", imageChoiceStage);
    }


    //以下用来产生逆序数
    public int[] random(int n) {    //生成n-1个不重复的逆序数为偶数的数字
        int[] ran = new int[n-1];
        while(iso(ran) == false) {
            ran = random_num(n);
        }
        return ran;

    }

    public int[] random_num(int n) {  //生成n-1个不重复数
        int r[] = new int[n-1];
        Random random = new Random();
        for(int i = 0; i < n-1; ++i) {
            r[i] = random.nextInt(n);
            for(int j = 0;j < i; ++j) {
                while(r[i] == r[j]) {
                    i--;
                    break;
                }
            }
        }
        return r;
    }

    public boolean iso(int[] num) {   //判断逆序数是否为偶数
        int sum = 0;
        for(int i = 0; i <= num.length-2; ++i) {
            for(int j = i; j <= num.length-1; j++) {
                if(num[i] > num[j]) {
                    sum++;
                }
            }
        }
        if((sum % 2) == 0 && sum != 0) {
            return true;
        }
        return false;
    }

    //生成白色空白图
    public Image generateImage(double red, double green, double blue, double opacity) {
        WritableImage img = new WritableImage(1, 1);
        PixelWriter pw = img.getPixelWriter();

        Color color = Color.color(red, green, blue, opacity);
        pw.setColor(0, 0, color);
        return img ;
    }

    class myevent implements EventHandler<MouseEvent> {    //点击事件的实现

        private int sizeOfImage;

        myevent(int num){
            sizeOfImage = num == 3 ? 100 : 75;
        }

        @Override
        public void handle(MouseEvent arg0) {
            // TODO Auto-generated method stub
            ImageView img = (ImageView) arg0.getSource();
            double sx = img.getLayoutX();
            double sy = img.getLayoutY();
            double dispx = sx - imageViews[m].getLayoutX();
            double dispy = sy - imageViews[m].getLayoutY();
            if((dispx == -1 * sizeOfImage) && (dispy == 0 )) {    //点击的空格左边的格子
                swapimg(img, imageViews[m]);    //交换imageView
                if(issucc(imageViews)) {       //判断是否拼成功
                    Alert alert = new Alert(AlertType.WARNING, "成功！");
                    alert.show();
                }
            }
            else if ((dispx == 0) && (dispy == -1 * sizeOfImage)) {  //上面的格子
                swapimg(img, imageViews[m]);
                if(issucc(imageViews)) {
                    Alert alert = new Alert(AlertType.WARNING, "成功！");
                    alert.show();
                }
            }
            else if((dispx == sizeOfImage) && (dispy == 0)) {    //右边的格子
                swapimg(img, imageViews[m]);
                if(issucc(imageViews)) {
                    Alert alert = new Alert(AlertType.WARNING, "成功！");
                    alert.show();
                }
            }
            else if((dispx == 0) && (dispy == sizeOfImage)) {    //下面的格子
                swapimg(img, imageViews[m]);
                if(issucc(imageViews)) {
                    Alert alert = new Alert(AlertType.WARNING, "成功！");
                    alert.show();
                }
            }
        }
        public void swapimg(ImageView i1, ImageView i2) {    //交换两个imageView的实现
            int row1 = GridPane.getRowIndex(i1);
            int colu1 = GridPane.getColumnIndex(i1);
            int row2 = GridPane.getRowIndex(i2);
            int colu2 = GridPane.getColumnIndex(i2);

            GridPane.setRowIndex(i1, row2);
            GridPane.setColumnIndex(i1, colu2);
            GridPane.setRowIndex(i2, row1);
            GridPane.setColumnIndex(i2, colu1);
        }
    }
    public boolean issucc(ImageView[] imageViews) {        //判断是否拼成功
        for(int i = 0; i <= 8; ++i) {
            if(i != 3 * GridPane.getRowIndex(imageViews[i]) + GridPane.getColumnIndex(imageViews[i])) {
                return false;
            }
        }
        return true;
    }

    public int findnum(int[] n) {            //找出m
        for(int j = 0; j <= n.length; ++j) {
//            int count = 0;
//            for(int i=0; i<n.length-1; ++i){
//                if(j==n[i]) break;
//                count += 1;
//            }
//            if(count == n.length) return j;
            if(n.length==8){
                if((j == n[0]) || (j == n[1]) || (j == n[2]) || (j == n[3]) || (j == n[4]) || (j == n[5]) || (j == n[6]) || (j == n[7])) {
                }
                else {
                    return j;
                }
            }
            else {
                if((j == n[0]) || (j == n[1]) || (j == n[2]) || (j == n[3]) || (j == n[4]) || (j == n[5]) || (j == n[6]) || (j == n[7]) ||
                   (j == n[8]) || (j == n[9]) || (j == n[10]) || (j == n[11]) || (j == n[12]) || (j == n[13]) || (j == n[14])){
                }
                else {
                    return j;
                }
            }

        }
        return -1;
    }


}
