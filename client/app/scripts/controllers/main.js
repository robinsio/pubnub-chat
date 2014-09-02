'use strict';

/**
 * @ngdoc function
 * @name clientApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the clientApp
 */
angular.module('clientApp')
  .controller('MainCtrl', function ($scope, $rootScope, message, PubNub) {

        $scope.messages = [];
        $scope.channel = 'my_channel';

        PubNub.init({
            subscribe_key: 'sub-c-d53f9f88-3282-11e4-a7b3-02ee2ddab7fe'
        });

        PubNub.ngSubscribe({ channel: $scope.channel });

        $rootScope.$on(PubNub.ngMsgEv($scope.channel), function(ngEvent, payload) {
            $scope.$apply(function() {
                $scope.messages.push(payload.message);
            });
        });

        $scope.save = function(m){
            message.save(m);
        }
  });
