# Skills

- Spring Boot
- Spring Webflux
- MongoDB

# Relations

`Cart` 1 : N `CartItem` 1 : 1 `Item` 

# Domain

`Item` (상품)
- 이름과 가격을 가짐

`CartItem`
- 상품과 수량을 갖고 있음

`Cart` (장바구니)
- `List<CartItem>` 을 갖고 있음
- 각 상품의 수량까지 포함해서 카트에 담고 있음

# 요구사항

상품 장바구니에 담기
- 현재 장바구니를 조회하고 없으면 비어 있는 새 장바구니 생성
- 장바구니에 담은 상품이 이미 장바구니에 있던 상품이라면 수량만 1 증가시킴
- 기존에 없던 상품이라면 상품 정보를 표시하고 수량을 1로 표시