<!-- 列表设置 -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="listDataModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title">固定数据</h4>
            </div>
            <div class="modal-body">
                <table class="table">
                    <thead>
                    <tr>
                        <th class="col-md-5">值</th>
                        <th class="col-md-5">文本</th>
                        <th>选中</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody></tbody>
                </table>
            </div>
            <div class="modal-footer">
                <div class="pull-left">
                    <button id="btnAddItem" type="button" class="btn btn-default">添加一项</button>
                </div>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button id="btnOk" type="button" class="btn btn-primary">确 定</button>
            </div>
        </div>
    </div>
</div>