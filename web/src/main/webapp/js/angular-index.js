angular.module('imApp', ['imService'])
    .controller('bodyController', function ($scope) {
        $scope.isLogin = false
    })
    .controller('loginController', function ($scope, loginService, userService) {
        $scope.login = function () {
            loginService.login($scope.username, $scope.password, function (result) {
                if (result.success) {
                    $scope.$parent.isLogin = true;
                    userService.user = result.result
                    console.log(userService.user)
                } else {
                    alert("登录失败")
                }
            })
        }
    })
    .controller('chatController', function ($scope, chatHistory, inputService, userService) {
        $scope.isLeft = function (message) {
            return message.chatId != userService.user.id
        }
        $scope.del = function (chat) {
            console.log(chat)
        };
        $scope.continueChat = function (chat) {
            chatHistory.getHistoryMessage(chat, function (result) {
                $scope.title = result.chatName;
                $scope.messages = result.messages;
            })
        };
        $scope.historys = [];

        $scope.send = function () {
            var content = inputService.getContent();
            if(!content){
                console.log('请输入文本!')
                return;
            }
            var user = userService.user;
            var message = {
                chatId: user.id,
                head: user.head,
                content: content
            }
            $scope.messages.push(message)
            inputService.clearContent()
            inputService.setFocus()
        }


        $scope.$watch('isLogin', function (isLogin) {
            //如果已经登录
            if (isLogin) {
                chatHistory.getHistory(function (result) {
                    if (result.success) {
                        $scope.historys = result.result
                    }
                });
            }
        })
    })