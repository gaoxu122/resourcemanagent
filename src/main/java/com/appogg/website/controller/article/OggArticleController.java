package com.appogg.website.controller.article;

import com.appogg.website.annotation.UserLoginToken;
import com.appogg.website.biz.article.OggArticleBiz;
import com.appogg.website.entity.OggArticle;
import com.appogg.website.msg.ObjectRestResponse;
import com.appogg.website.msg.TableResultResponse;
import com.appogg.website.rest.BaseController;
import com.appogg.website.util.Query;
import com.appogg.website.vo.article.ArticleVo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/article/")
public class OggArticleController extends BaseController<OggArticleBiz,OggArticle> {

    @UserLoginToken
    @GetMapping("sss")
    public void hello(){
        System.out.println("....sss");
    }


    /**
     * 需要登录才能发文章
     * @param articleVo
     * @return
     */
    @UserLoginToken
    @PostMapping("add")
    public ObjectRestResponse insertArticle(@RequestBody ArticleVo articleVo,HttpServletRequest request){
        return this.baseBiz.insertArticleMsg(articleVo,request,new Byte((byte) 0));
    }
    /**
     * 保存文章不发布
     * @param articleVo
     * @return
     */
    @UserLoginToken
    @PostMapping("save")
    public ObjectRestResponse saveArticle(@RequestBody ArticleVo articleVo,HttpServletRequest request){
        return this.baseBiz.insertArticleMsg(articleVo,request,new Byte((byte) 1));
    }

    @GetMapping("list")
    public TableResultResponse listPublicArticle(@RequestParam Map<String,Object> params){
        Query query = new Query(params);
        return this.baseBiz.listPublicArticleMsg(query);
    }

    /**
     * 列出所有权限的文章
     * @param params
     * @return
     */
    @GetMapping("listAll")
    public TableResultResponse listAllArticle(@RequestParam Map<String,Object> params){
        Query query = new Query(params);
        return this.baseBiz.listAllArticle(query);
    }
    @GetMapping("trendingList")
    public TableResultResponse listTrendingSoft(@RequestParam Map<String,Object> params){

        Query query = new Query(params);
        return this.baseBiz.listTrendingArticleMsg(query);
    }
    @GetMapping("authorTrending")
    public TableResultResponse listAuthorTrendingSoft(@RequestParam Map<String,Object> params){
        Query query = new Query(params);
        return this.baseBiz.listAuthorTrendingArticle(query);
    }

    @GetMapping("detail")
    public ObjectRestResponse selectArticleDetail(@RequestParam Map<String,Object> params){
        Query query = new Query(params);
        return  this.baseBiz.selectArticleDetail(query);
    }
    @GetMapping("updateReadNum")
    public ObjectRestResponse uploadArticleReadNum(@RequestParam Map<String,Object> params){
        Query query = new Query(params);
        return  this.baseBiz.updateArticleReadNum(query);
    }

    @PostMapping("uploadTitleImage")
    public ObjectRestResponse uploadArticleTitleImage(HttpServletRequest request, @RequestParam("file") MultipartFile multipartFile){
        return  this.baseBiz.uploadArticleTitleImage(request, multipartFile);
    }


}
