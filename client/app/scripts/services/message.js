'use strict';

/**
 * @ngdoc service
 * @name clientApp.message
 * @description
 * # message
 * Service in the clientApp.
 */
angular.module('clientApp')
    .service('message', function silk($resource, API_URL) {
        return $resource(API_URL + '/message');
    });