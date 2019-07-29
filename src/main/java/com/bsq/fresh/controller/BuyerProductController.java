package com.bsq.fresh.controller;

import com.bsq.fresh.Vo.ResultFoodVO;
import com.bsq.fresh.Vo.ResultVO;
import com.bsq.fresh.Vo.entityVO.*;
import com.bsq.fresh.entity.*;
import com.bsq.fresh.service.*;
import com.bsq.fresh.utils.ResultFoodVOUtil;
import com.bsq.fresh.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/buyer/product")
public class BuyerProductController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryTwoService categoryTwoService;
    @Autowired
    private CategoryTwoFoodService categoryTwoFoodService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
    /**
     * 查询全部商品
     * @return
     */
    @GetMapping("/list")
    public ResultVO<List<CategoryVO>> categoryList()
    {
        List<CategoryTwoFood> categoryTwoFoodList =categoryTwoFoodService.findAll();
        List<CategoryTwo> categoryTwoList = categoryTwoService.findAll();
        List<Category> list=categoryService.findAll();
        List<Comment> commentList = commentService.findAll();
        List<User> userList = userService.findAll();

        List<CategoryVO> categoryVOList = new ArrayList<>();
        for (Category category:list
                ) {
            CategoryVO categoryVO = new CategoryVO();
            categoryVO.setCategoryId(category.getCategoryId());
            categoryVO.setCategoryName(category.getCategoryName());

            //二级分类商品
            List<CategoryTwoVO> categoryTwoVOList = new ArrayList<>();
            for (CategoryTwo categoryTwo:categoryTwoList) {
                if(String.valueOf(category.getCategoryId()).equals(String.valueOf(categoryTwo.getCategoryId()))){
                    CategoryTwoVO categoryTwoVO = new CategoryTwoVO();
                    categoryTwoVO.setCategoryTwoId(categoryTwo.getCategoryTwoId());
                    categoryTwoVO.setCategoryTwoImage(categoryTwo.getCategoryTwoImage());
                    categoryTwoVO.setCategoryTwoName(categoryTwo.getCategoryTwoName());
                    categoryTwoVOList.add(categoryTwoVO);
                }
            }
            categoryVO.setCategoryTwoVOList(categoryTwoVOList);
            categoryVOList.add(categoryVO);
        }

        return ResultVOUtil.success(categoryVOList);
    }


    @GetMapping("/categoryFoodList")
    public ResultFoodVO<List<CategoryTwoFoodVO>> categoryFoodList()
    {
        List<CategoryTwoFood> categoryTwoFoodList =categoryTwoFoodService.findAll();
        List<Comment> commentList = commentService.findAll();
        List<User> userList = userService.findAll();

        List<CategoryTwoFoodVO> categoryTwoFoodVOList = new ArrayList<>();
        for (CategoryTwoFood categoryTwoFood:categoryTwoFoodList) {
                CategoryTwoFoodVO categoryTwoFoodVO = new CategoryTwoFoodVO();
                categoryTwoFoodVO.setCategoryTwoFoodId(categoryTwoFood.getCategoryTwoFoodId());
                categoryTwoFoodVO.setCategoryTwoFoodDesc(categoryTwoFood.getCategoryTwoFoodDesc());
                categoryTwoFoodVO.setCategoryTwoFoodImage(categoryTwoFood.getCategoryTwoFoodImage());
                categoryTwoFoodVO.setCategoryTwoFoodPrice(categoryTwoFood.getCategoryTwoFoodPrice());
                categoryTwoFoodVO.setCategoryTwoFoodStock(categoryTwoFood.getCategoryTwoFoodStock());
                categoryTwoFoodVO.setCategoryTwoFoodOriginPrice(categoryTwoFood.getCategoryTwoFoodOriginPrice());
                //评论
                int commentCount = 0;//评论条数
                double commentScore = 0;//评论分数
                double commentScore1=0;//比率
                for (Comment comment:commentList) {
                    if (String.valueOf(comment.getCategoryTwoFoodId()).equals(String.valueOf(categoryTwoFood.getCategoryTwoFoodId()))) {
                        for (User user:userList) {
                            if(String.valueOf(user.getUserId()).equals(String.valueOf(comment.getUserId()))){
                                commentCount++;
                                commentScore += comment.getCommentScore();
                            }
                        }
                    }
                }
                categoryTwoFoodVO.setComment(commentCount);//评论条数
                categoryTwoFoodVO.setRate(commentScore);//评分（好评率）
                categoryTwoFoodVOList.add(categoryTwoFoodVO);

        }


        return ResultFoodVOUtil.success(categoryTwoFoodVOList);
    }


    @GetMapping("/CommentList")
    public ResultVO<List<CategoryVO>> CommentList()
    {
        List<CategoryTwoFood> categoryTwoFoodList =categoryTwoFoodService.findAll();
        List<CategoryTwo> categoryTwoList = categoryTwoService.findAll();
        List<Category> list=categoryService.findAll();
        List<Comment> commentList = commentService.findAll();
        List<User> userList = userService.findAll();

        List<CategoryVO> categoryVOList = new ArrayList<>();
        for (Category category:list
                ) {
            CategoryVO categoryVO = new CategoryVO();
            categoryVO.setCategoryId(category.getCategoryId());
            categoryVO.setCategoryName(category.getCategoryName());

            //二级分类商品
            List<CategoryTwoVO> categoryTwoVOList = new ArrayList<>();
            for (CategoryTwo categoryTwo:categoryTwoList) {
                if(String.valueOf(category.getCategoryId()).equals(String.valueOf(categoryTwo.getCategoryId()))){
                    CategoryTwoVO categoryTwoVO = new CategoryTwoVO();
                    categoryTwoVO.setCategoryTwoId(categoryTwo.getCategoryTwoId());
                    categoryTwoVO.setCategoryTwoImage(categoryTwo.getCategoryTwoImage());
                    categoryTwoVO.setCategoryTwoName(categoryTwo.getCategoryTwoName());
                    List<CategoryTwoFoodVO> categoryTwoFoodVOList = new ArrayList<>();
                    for (CategoryTwoFood categoryTwoFood:categoryTwoFoodList
                            ) {
                        if(String.valueOf(categoryTwoFood.getCategoryTwoId()).equals(String.valueOf(categoryTwo.getCategoryTwoId()))){
                            CategoryTwoFoodVO categoryTwoFoodVO = new CategoryTwoFoodVO();
                            categoryTwoFoodVO.setCategoryTwoFoodId(categoryTwoFood.getCategoryTwoFoodId());
                            categoryTwoFoodVO.setCategoryTwoFoodDesc(categoryTwoFood.getCategoryTwoFoodDesc());
                            categoryTwoFoodVO.setCategoryTwoFoodImage(categoryTwoFood.getCategoryTwoFoodImage());
                            categoryTwoFoodVO.setCategoryTwoFoodPrice(categoryTwoFood.getCategoryTwoFoodPrice());
                            categoryTwoFoodVO.setCategoryTwoFoodStock(categoryTwoFood.getCategoryTwoFoodStock());
                            //评论
                            List<CommentVO> comments = new ArrayList<>();
                            for (Comment comment:commentList) {
                                if(String.valueOf(comment.getCategoryTwoFoodId()).equals(String.valueOf(categoryTwoFood.getCategoryTwoFoodId()))){
                                    CommentVO commentVO = new CommentVO();
                                    commentVO.setCategoryTwoFoodId(comment.getCategoryTwoFoodId());
                                    commentVO.setCommentDesc(comment.getCommentDesc());
                                    commentVO.setCommentId(comment.getCommentId());
                                    commentVO.setCommentScore(comment.getCommentScore());
                                    commentVO.setCommentTime(comment.getCommentTime());
                                    List<UserVO> userVOList = new ArrayList<>();
                                    for (User user:userList) {
                                        if(String.valueOf(user.getUserId()).equals(String.valueOf(comment.getUserId()))){
                                            UserVO u = new UserVO();
                                            u.setUserId(user.getUserId());
                                            u.setUserPhone(user.getUserPhone());
                                            u.setUserUsername(user.getUserUsername());
                                            u.setUserHeaderImg(user.getUserHeaderImg());
                                            userVOList.add(u);
                                        }
                                    }
                                    commentVO.setUserList(userVOList);
                                    comments.add(commentVO);
                                }
                            }
                            categoryTwoFoodVO.setCommentList(comments);
                            categoryTwoFoodVOList.add(categoryTwoFoodVO);
                        }
                    }
                    categoryTwoVO.setCategoryTwoFoodVOList(categoryTwoFoodVOList);
                    categoryTwoVOList.add(categoryTwoVO);

                }
            }
            categoryVO.setCategoryTwoVOList(categoryTwoVOList);
            categoryVOList.add(categoryVO);
        }

        return ResultVOUtil.success(categoryVOList);
    }


}
