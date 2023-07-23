package jp.kobe_u.cs.daikibo.tsubuyaki.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;
import jp.kobe_u.cs.daikibo.tsubuyaki.entity.Tsubuyaki;
import jp.kobe_u.cs.daikibo.tsubuyaki.service.TsubuyakiService;

import org.springframework.beans.factory.annotation.Autowired;
@Controller

  
public class TsubuyakiController {

  
    @Autowired
    TsubuyakiService ts;
    
    //タイトル画面を表示
    @GetMapping("/")

    String showIndex() {
        return "index";
    }

  
    //メイン画面を表示
    @GetMapping("/read")
    String showTsubuyakiList(Model model) {
 
        List<Tsubuyaki> list = ts.getAllTsubuyaki(); //全つぶやきを取得
        model.addAttribute("tsubuyakiList", list);   //モデル属性にリストをセット
        model.addAttribute("tsubuyakiForm", new TsubuyakiForm());  //空フォームをセット

        return "tsubuyaki_list"; //リスト画面を返す 
    }

  
    //つぶやきを投稿
    @PostMapping("/read")
    String postTsubuyaki(@ModelAttribute("tsubuyakiForm") TsubuyakiForm form, Model model) {

  
        //フォームからエンティティに移し替え
        Tsubuyaki t = new Tsubuyaki();
        t.setName(form.getName());
        t.setComment(form.getComment());

        //サービスに投稿処理を依頼
        ts.postTsubuyaki(t);

        return "redirect:/read"; //メイン画面に転送
    }

// つぶやきを検索
    @GetMapping("/search")
    String searchTsubuyaki(@ModelAttribute("keyword") String keyword, Model model) {
        List<Tsubuyaki> searchResult = ts.searchTsubuyaki(keyword); // カスタム・クエリを使って検索
        model.addAttribute("tsubuyakiList", searchResult); // モデル属性にリストをセット
        model.addAttribute("tsubuyakiForm", new TsubuyakiForm()); // 空フォームをセット

        return "tsubuyaki_list"; // 検索結果を表示するビューの名前を返す
    }
}