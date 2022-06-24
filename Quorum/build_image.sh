docker buildx create --name quorum_builder --node quorum_builder0 --use
docker buildx build --no-cache -f Dockerfile.quorum.build -t andreasstefik/quorum:10.0.5 . --platform linux/amd64,linux/arm64 --push
