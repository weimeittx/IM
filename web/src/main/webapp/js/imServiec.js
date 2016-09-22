angular.module('imService', [])
    .service("userService", function () {
        this.user = undefined;
    })
    .service('inputService', function () {
        var ue = UE.getEditor('editor');
        this.getContent = function () {
            return ue.getContent();
        };
        this.setContent = function (message) {
            ue.setContent(message, false)
        };
        this.clearContent = function () {
            ue.setContent("", false)
        };
        //获取焦点
        this.setFocus = function(){
            ue.focus()
        };
    }
    )
    .service('chatHistory', function ($http, userService) {
        /**
         * 获取历史聊天对象
         * @param f
         */
        this.getHistory = function (f) {
            if (userService.user) {
                $http({
                    url: '/IM/web/src/main/webapp/data/chatHistory.json'
                }).success(function (result) {
                    f(result)
                })
            }
        }
        /**
         * 获取历史聊天消息
         */
        this.getHistoryMessage = function (history, f) {
            $http({
                url: "/IM/web/src/main/webapp/data/historyMessage.json",
                data: {
                    to: history.id,
                    type: history.type
                }
            }).success(function (result) {
                f(result);
            })
        }
    })
    .service('chatService', function ($http, userService) {
        this.getChat = function () {
            if (userService.user) {

            }
        };

    })
    .service('chatGroupService', function ($http, userService) {
        this.getChatGroup = function () {
            if (userService.user) {

            }
        }
    })
    .service("loginService", function ($http) {
        this.login = function (username, password, f) {
            $http({
                url: "/IM/web/src/main/webapp/data/login.json",
                method: "post",
                data: {
                    username: username,
                    password: password
                }
            }).success(function (data) {
                f(data);
            }).error(function () {
                alert("网络异常!");
            })
        }
    });