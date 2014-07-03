#!/bin/bash
closure-compiler --js `find ./web/resources/js/*.js` --js_output_file ./build/out.js