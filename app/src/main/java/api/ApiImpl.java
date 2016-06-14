package api;

import android.util.Pair;

import com.htlc.muchong.util.LoginUtil;
import com.larno.util.okhttp.callback.ResultCallback;
import com.larno.util.okhttp.request.OkHttpRequest;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import model.UserBean;

/**
 * Api实现类
 */
public class ApiImpl implements Api {
    @Override
    public void smsCode(String mobile, ResultCallback callback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("mobile", mobile);
        String url = Api.SmsCode;
        new OkHttpRequest.Builder().url(url).params(params).post(callback);
    }

    @Override
    public void register(String user_account, String Verifycode, String user_pwda, String user_pwdb, ResultCallback callback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("user_account", user_account);
        params.put("Verifycode", Verifycode);
        params.put("user_pwda", user_pwda);
        params.put("user_pwdb", user_pwdb);
        String url = Api.Register;
        new OkHttpRequest.Builder().url(url).params(params).post(callback);
    }

    @Override
    public void login(String user_account, String user_pwd, ResultCallback callback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("user_account", user_account);
        params.put("user_pwd", user_pwd);
        String url = Api.Login;
        new OkHttpRequest.Builder().url(url).params(params).post(callback);
    }

    @Override
    public void getUserInfo(ResultCallback callback) {
        UserBean user = LoginUtil.getUser();
        Map<String, String> params = new HashMap<String, String>();
        params.put("user_id", user.id);
        params.put("user_token", user.user_token);
        String url = Api.GetUserInfo;
        new OkHttpRequest.Builder().url(url).params(params).post(callback);
    }

    @Override
    public void updateUserInfo(String userinfo_nickname, String userinfo_address, File userinfo_headportrait, ResultCallback callback) {
        UserBean user = LoginUtil.getUser();
        Map<String, String> params = new HashMap<String, String>();
        params.put("user_id", user.id);
        params.put("user_token", user.user_token);
        params.put("userinfo_nickname", userinfo_nickname);
        params.put("userinfo_address", userinfo_address);
        String url = Api.UpdateUserInfo;
        if (userinfo_headportrait != null) {
            Pair<String, File> pair = new Pair<>("userinfo_headportrait", userinfo_headportrait);
            new OkHttpRequest.Builder().url(url).params(params).files(pair).upload(callback);
        } else {
            new OkHttpRequest.Builder().url(url).params(params).post(callback);
        }
    }

    @Override
    public void resetPassword(String user_account, String Verifycode, String user_pwda, String user_pwdb, ResultCallback callback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("user_account", user_account);
        params.put("Verifycode", Verifycode);
        params.put("user_pwda", user_pwda);
        params.put("user_pwdb", user_pwdb);
        String url = Api.ResetPassword;
        new OkHttpRequest.Builder().url(url).params(params).post(callback);
    }

    @Override
    public void home(ResultCallback callback) {
        String url = Api.Home;
        new OkHttpRequest.Builder().url(url).get(callback);
    }

    @Override
    public void getGoodsType(ResultCallback callback) {
        String url = Api.GetGoodsType;
        new OkHttpRequest.Builder().url(url).get(callback);
    }

    @Override
    public void getPointInTimes(ResultCallback callback) {
        String url = Api.GetPaiStartTimes;
        new OkHttpRequest.Builder().url(url).get(callback);
    }

    @Override
    public void publishGoods(String commodity_name, String commodity_content,
                             String commodity_type, String commodity_smallclass, String commodity_spec, String commodity_material, String commodity_panicprice,
                             String commodity_starttime, String commodity_limitend, String commodity_buynum, String commodity_price, String commodity_bond,
                             Pair<String, File>[] images, ResultCallback callback) {
        UserBean user = LoginUtil.getUser();
        Map<String, String> params = new HashMap<String, String>();
        params.put("user_id", user.id);
        params.put("user_token", user.user_token);

        params.put("commodity_name", commodity_name);
        params.put("commodity_content", commodity_content);

        params.put("commodity_type", commodity_type);
        params.put("commodity_smallclass", commodity_smallclass);
        params.put("commodity_spec", commodity_spec);
        params.put("commodity_material", commodity_material);
        params.put("commodity_panicprice", commodity_panicprice);

        params.put("commodity_starttime", commodity_starttime);
        params.put("commodity_timelimit", commodity_limitend);
        params.put("commodity_buynum", commodity_buynum);
        params.put("commodity_price", commodity_price);
        params.put("commodity_bond", commodity_bond);

        String url = Api.PublishGoods;
        new OkHttpRequest.Builder().url(url).params(params).files(images).upload(callback);

    }

    @Override
    public void goodsDetail(String commodity_id, ResultCallback callback) {
        UserBean user = LoginUtil.getUser();
        Map<String, String> params = new HashMap<String, String>();
        params.put("user_id", user.id);
        params.put("user_token", user.user_token);

        params.put("commodity_id", commodity_id);
        String url = Api.GoodsDetail;
        new OkHttpRequest.Builder().url(url).params(params).post(callback);
    }

    @Override
    public void goodsCommentList(String commodity_id,String page, ResultCallback callback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("commodity_id", commodity_id);
        params.put("page", page);
        String url = Api.GoodsCommentList;
        new OkHttpRequest.Builder().url(url).params(params).post(callback);
    }

    @Override
    public void addGoodsComment(String commodityeval_commodityid, String commodityeval_content, ResultCallback callback) {
        UserBean user = LoginUtil.getUser();
        Map<String, String> params = new HashMap<String, String>();
        params.put("user_id", user.id);
        params.put("user_token", user.user_token);

        params.put("commodityeval_commodityid", commodityeval_commodityid);
        params.put("commodityeval_content", commodityeval_content);
        String url = Api.AddGoodsComment;
        new OkHttpRequest.Builder().url(url).params(params).post(callback);
    }

    @Override
    public void qiangTimeList(ResultCallback callback) {
        String url = Api.QiangTimeList;
        new OkHttpRequest.Builder().url(url).get(callback);
    }

    @Override
    public void qiangList(String flag, String page, ResultCallback callback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("flag", flag);
        params.put("page", page);
        String url = Api.QiangList;
        new OkHttpRequest.Builder().url(url).params(params).post(callback);
    }

    @Override
    public void paiList(String page, ResultCallback callback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("page", page);
        String url = Api.PaiList;
        new OkHttpRequest.Builder().url(url).params(params).post(callback);
    }

    @Override
    public void jiaoList(String page, ResultCallback callback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("page", page);
        String url = Api.JiaoList;
        new OkHttpRequest.Builder().url(url).params(params).post(callback);
    }

    @Override
    public void jiaoListBySmallClass(String page, String commodity_smallclass, String order, String commodity_material, ResultCallback callback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("page", page);
        params.put("commodity_smallclass", commodity_smallclass);
        params.put("order", order);
        params.put("commodity_material", commodity_material);
        String url = Api.JiaoList;
        new OkHttpRequest.Builder().url(url).params(params).post(callback);
    }

    @Override
    public void addLike(String commodityid, String forumid, String user, ResultCallback callback) {
        UserBean bean = LoginUtil.getUser();
        Map<String, String> params = new HashMap<String, String>();
        params.put("user_id", bean.id);
        params.put("user_token", bean.user_token);

        params.put("commodityid", commodityid);
        params.put("forumid", forumid);
        params.put("user", user);
        String url = Api.AddLike;
        new OkHttpRequest.Builder().url(url).params(params).post(callback);
    }

    @Override
    public void addShoppingCart(String shopcar_commodityid, ResultCallback callback) {
        UserBean bean = LoginUtil.getUser();
        Map<String, String> params = new HashMap<String, String>();
        params.put("user_id", bean.id);
        params.put("user_token", bean.user_token);

        params.put("shopcar_commodityid", shopcar_commodityid);
        String url = Api.AddShoppingCart;
        new OkHttpRequest.Builder().url(url).params(params).post(callback);
    }

    @Override
    public void buyNow(String commodity_id, String num, String address_id, ResultCallback callback) {
        UserBean bean = LoginUtil.getUser();
        Map<String, String> params = new HashMap<String, String>();
        params.put("user_id", bean.id);
        params.put("user_token", bean.user_token);

        params.put("commodity_id", commodity_id);
        params.put("num", num);
        params.put("address_id", address_id);
        String url = Api.BuyNow;
        new OkHttpRequest.Builder().url(url).params(params).post(callback);
    }

    @Override
    public void buyByShoppingCart(String shopcar, String address_id, ResultCallback callback) {
        UserBean bean = LoginUtil.getUser();
        Map<String, String> params = new HashMap<String, String>();
        params.put("user_id", bean.id);
        params.put("user_token", bean.user_token);

        params.put("shopcar", shopcar);
        params.put("address_id", address_id);
        String url = Api.BuyByShoppingCart;
        new OkHttpRequest.Builder().url(url).params(params).post(callback);
    }

    @Override
    public void shoppingCartList(ResultCallback callback) {
        UserBean bean = LoginUtil.getUser();
        Map<String, String> params = new HashMap<String, String>();
        params.put("user_id", bean.id);
        params.put("user_token", bean.user_token);

        String url = Api.ShoppingCartList;
        new OkHttpRequest.Builder().url(url).params(params).post(callback);
    }

    @Override
    public void deleteShoppingCart(String shopcar_id, ResultCallback callback) {
        UserBean bean = LoginUtil.getUser();
        Map<String, String> params = new HashMap<String, String>();
        params.put("user_id", bean.id);
        params.put("user_token", bean.user_token);

        params.put("shopcar_id", shopcar_id);
        String url = Api.DeleteShoppingCart;
        new OkHttpRequest.Builder().url(url).params(params).post(callback);
    }

    @Override
    public void addPaiPrice(String commodity_id, String price, ResultCallback callback) {
        UserBean bean = LoginUtil.getUser();
        Map<String, String> params = new HashMap<String, String>();
        params.put("user_id", bean.id);
        params.put("user_token", bean.user_token);

        params.put("commodity_id", commodity_id);
        params.put("price", price);
        String url = Api.AddPaiPrice;
        new OkHttpRequest.Builder().url(url).params(params).post(callback);
    }

    @Override
    public void cangList(String page,ResultCallback callback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("page", page);
        String url = Api.CangList;
        new OkHttpRequest.Builder().url(url).params(params).post(callback);
    }

    @Override
    public void postDetail(String forum_id, ResultCallback callback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("forum_id", forum_id);
        String url = Api.PostDetail;
        new OkHttpRequest.Builder().url(url).params(params).post(callback);
    }

    @Override
    public void postCommentList(String forum_id, String page, ResultCallback callback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("forum_id", forum_id);
        params.put("page", page);
        String url = Api.PostCommentList;
        new OkHttpRequest.Builder().url(url).params(params).post(callback);
    }

    @Override
    public void addPostComment(String forum_backid, String forum_content, ResultCallback callback) {
        UserBean bean = LoginUtil.getUser();
        Map<String, String> params = new HashMap<String, String>();
        params.put("user_id", bean.id);
        params.put("user_token", bean.user_token);

        params.put("forum_backid", forum_backid);
        params.put("forum_content", forum_content);
        String url = Api.AddPostComment;
        new OkHttpRequest.Builder().url(url).params(params).post(callback);
    }

    @Override
    public void publishPost(String forum_title, String forum_content, String forum_type, Pair<String, File>[] images, ResultCallback callback) {
        UserBean user = LoginUtil.getUser();
        Map<String, String> params = new HashMap<String, String>();
        params.put("user_id", user.id);
        params.put("user_token", user.user_token);

        params.put("forum_title", forum_title);
        params.put("forum_content", forum_content);

        params.put("forum_type", forum_type);
        String url = Api.PublishPost;
        new OkHttpRequest.Builder().url(url).params(params).files(images).upload(callback);
    }

    @Override
    public void jianList(String page, String forum_yesorno, ResultCallback callback) {
        Map<String, String> params = new HashMap<String, String>();

        params.put("page", page);
        params.put("forum_yesorno", forum_yesorno);
        String url = Api.JianList;
        new OkHttpRequest.Builder().url(url).params(params).post(callback);
    }

    @Override
    public void publishJianResult(String appraisal_forumid, String appraisal_type, String appraisal_content, ResultCallback callback) {
        UserBean user = LoginUtil.getUser();
        Map<String, String> params = new HashMap<String, String>();
        params.put("user_id", user.id);
        params.put("user_token", user.user_token);

        params.put("appraisal_forumid", appraisal_forumid);
        params.put("appraisal_type", appraisal_type);
        params.put("appraisal_content", appraisal_content);
        String url = Api.PublishJianResult;
        new OkHttpRequest.Builder().url(url).params(params).post(callback);
    }

    @Override
    public void postList(String user_id, String page, ResultCallback callback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("user_id", user_id);
        params.put("page", page);
        String url = Api.PostList;
        new OkHttpRequest.Builder().url(url).params(params).post(callback);
    }

    @Override
    public void schoolList(String user_id, String page, ResultCallback callback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("user_id", user_id);
        params.put("page", page);
        String url = Api.SchoolList;
        new OkHttpRequest.Builder().url(url).params(params).post(callback);
    }

    @Override
    public void activityList(String page, ResultCallback callback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("page", page);
        String url = Api.ActivityList;
        new OkHttpRequest.Builder().url(url).params(params).post(callback);
    }

    @Override
    public void personList(String page, ResultCallback callback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("page", page);
        String url = Api.PersonList;
        new OkHttpRequest.Builder().url(url).params(params).post(callback);
    }

    @Override
    public void cangListByPersonId(String page, String user_id, ResultCallback callback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("page", page);
        params.put("user_id", user_id);
        String url = Api.CangListByPersonId;
        new OkHttpRequest.Builder().url(url).params(params).post(callback);
    }

    @Override
    public void getPersonInfo(String id, ResultCallback callback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id);
        String url = Api.GetPersonInfo;
        new OkHttpRequest.Builder().url(url).params(params).post(callback);
    }

    @Override
    public void likeListByType(String page, String id, String type, ResultCallback callback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("page", page);
        params.put("id", id);
        params.put("type", type);
        String url = Api.LikeListByType;
        new OkHttpRequest.Builder().url(url).params(params).post(callback);
    }
}
